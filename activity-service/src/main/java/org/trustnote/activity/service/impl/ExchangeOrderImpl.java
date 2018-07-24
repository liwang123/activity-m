package org.trustnote.activity.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.dto.ExchangeOrderDTO;
import org.trustnote.activity.common.model.ResponseResult;
import org.trustnote.activity.common.pojo.CheckAccount;
import org.trustnote.activity.common.pojo.ExchangeOrder;
import org.trustnote.activity.common.utils.DateTimeUtils;
import org.trustnote.activity.common.utils.OkHttpUtils;
import org.trustnote.activity.common.utils.Sending;
import org.trustnote.activity.common.utils.SendingPool;
import org.trustnote.activity.service.iface.ExchangeOrderService;
import org.trustnote.activity.skeleton.mybatis.mapper.CheckAccountMapper;
import org.trustnote.activity.skeleton.mybatis.mapper.ExchangeOrderMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeOrderImpl implements ExchangeOrderService {
    @Autowired
    private ExchangeOrderMapper exchangeOrderMapper;

    @Autowired
    private CheckAccountMapper checkAccountMapper;


    @Override
    public synchronized void insertExchangeOrder(final ExchangeOrderDTO exchangeOrderDTO) {
        final BigDecimal rate = this.getRate();
        final ExchangeOrder exchangeOrder = ExchangeOrder.builder()
                .currency(exchangeOrderDTO.getCurrency())
                .deviceAddress(exchangeOrderDTO.getDeviceAddress())
                .tttAddress(exchangeOrderDTO.getTttAddress())
                .toAddress(exchangeOrderDTO.getToAddress())
                .inviteCode(exchangeOrderDTO.getInviteCode())
                .payment(exchangeOrderDTO.getPayment())
                .states(1)
                .rate(rate).build();
        this.exchangeOrderMapper.insertSelective(exchangeOrder);
    }

    @Override
    public Page getAllOrder(final int pageIndex, final int pageSize, final int status) {
        final Page<ExchangeOrder> page = new Page<>(pageIndex, pageSize);
        final List<ExchangeOrder> exchangeOrderList = this.exchangeOrderMapper.selectByPage((pageIndex - 1) * pageSize, pageSize, status);
        exchangeOrderList.stream()
                .forEach(exchangeOrder -> {
                    exchangeOrder.setCreTime(DateTimeUtils.formatDateTime(exchangeOrder.getCreateTime(), DateTimeUtils.secondPattern));
                });
        page.setResult(exchangeOrderList);
        page.setTotalCount(this.exchangeOrderMapper.countByOrder(status));
        return page;
    }

    @Override
    public ResponseResult manualMoney(final Long id) {
        final ExchangeOrder exchangeOrder = this.exchangeOrderMapper.selectByPrimaryKey(id);
        final BigDecimal rate = this.getRate();
        if (rate.compareTo(new BigDecimal(0)) == 0) {
            return ResponseResult.failure(3004, "getRate connect timeout");
        }
        exchangeOrder.setRate(rate);
        final BigDecimal checkBalance = this.checkBalance();
        if (checkBalance.compareTo(new BigDecimal(-1)) == 0) {
            return ResponseResult.failure(3007, "checkBalance connect timeout");
        }
        final BigDecimal quantity = exchangeOrder.getReceipt().divide(exchangeOrder.getRate());
        if (checkBalance.compareTo(quantity) != 1) {
            exchangeOrder.setStates(2);
            this.exchangeOrderMapper.updateByPrimaryKeySelective(exchangeOrder);
            this.sendMail(exchangeOrder);
            return ResponseResult.failure(3005, "NOT ENOUGH MONEY");
        }
        exchangeOrder.setQuantity(quantity);
        //转账
        final String url = "http://150.109.32.56:9000/payToAddress";
        final Map<String, String> param = new HashMap<>();
        param.put("address", exchangeOrder.getTttAddress());
        param.put("amount", exchangeOrder.getQuantity().toString());
        final String body = OkHttpUtils.post(url, param);
        if (body == null) {
            return ResponseResult.failure(3008, "payToAddress connect timeout");
        }
        final JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
        final int code = (int) jsonObject.get("code");
        if (code == 200) {
            exchangeOrder.setStates(4);
        } else if (code == 501) {
            exchangeOrder.setStates(2);
            this.sendMail(exchangeOrder);
        } else {
            exchangeOrder.setStates(6);
            this.exchangeOrderMapper.updateByPrimaryKeySelective(exchangeOrder);
            return ResponseResult.failure(3006, body);
        }
        this.exchangeOrderMapper.updateByPrimaryKeySelective(exchangeOrder);
        this.addRecord(exchangeOrder);
        //调取推送设备信息接口
        final String postTransferResult = this.postTransferResult(exchangeOrder);
        return ResponseResult.success(postTransferResult);
    }

    private String postTransferResult(final ExchangeOrder exchangeOrder) {
        final String deviceUrl = "http://150.109.32.56:9000/postTransferResult";
        final HashMap<String, String> params = new HashMap<>();
        params.put("device_address", exchangeOrder.getDeviceAddress());
        params.put("rate", exchangeOrder.getRate().toString());
        params.put("amount", exchangeOrder.getQuantity().toString());
        params.put("state", "Success");
        params.put("ttt_address", exchangeOrder.getTttAddress());
        final String deciveBody = OkHttpUtils.post(deviceUrl, params);
        return deciveBody;
    }

    @Override
    public BigDecimal checkBalance() {
        final String url = "http://150.109.32.56:9000/getWalletBalance";
        final String body = OkHttpUtils.get(url, null);
        if (body == null) {
            return new BigDecimal(-1);
        }
        final JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
        final BigDecimal checkBanlance = new BigDecimal(jsonObject.getJSONObject("data").get("balance").toString());
        return checkBanlance;
    }

    @Override
    public String getExchangeOrder() {

        return null;
    }


    @Override
    public BigDecimal getRate() {
        final String url = "https://api.bit-z.pro/api_v1/ticker";
        final Map<String, String> map = new HashMap<>();
        map.put("coin", "ttt_btc");
        final String body = OkHttpUtils.get(url, map);
        if (body == null) {
            return new BigDecimal(0);
        }
        final JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
        return new BigDecimal(jsonObject.getJSONObject("data").get("last").toString());
    }

    private void addRecord(final ExchangeOrder exchangeOrder) {
        final CheckAccount checkAccount = CheckAccount.builder()
                .exchangeId(exchangeOrder.getId())
                .fromAddress(exchangeOrder.getFromAddress())
                .toAddress(exchangeOrder.getToAddress())
                .amount(exchangeOrder.getQuantity())
                .build();
        this.checkAccountMapper.insertSelective(checkAccount);
    }

    @Override
    public void sendMail(final ExchangeOrder exchangeOrder) {
        final SendingPool pool = SendingPool.getInstance();
        pool.addThread(new Sending("13333611437@qq.com", "TrustNote email", "有订单未处理" + exchangeOrder));
    }
}
