package org.trustnote.activity.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.dto.ConfirmBalanceDTO;
import org.trustnote.activity.common.dto.ExchangeEmailOrderDTO;
import org.trustnote.activity.common.dto.ExchangeOrderDTO;
import org.trustnote.activity.common.enume.StatesEnum;
import org.trustnote.activity.common.model.ResponseResult;
import org.trustnote.activity.common.pojo.CheckAccount;
import org.trustnote.activity.common.pojo.ExchangeOrder;
import org.trustnote.activity.common.pojo.ExchangeRate;
import org.trustnote.activity.common.utils.*;
import org.trustnote.activity.service.iface.ExchangeOrderService;
import org.trustnote.activity.skeleton.mybatis.mapper.CheckAccountMapper;
import org.trustnote.activity.skeleton.mybatis.mapper.ExchangeOrderMapper;
import org.trustnote.activity.skeleton.mybatis.mapper.ExchangeRateMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeOrderImpl implements ExchangeOrderService {
    @Autowired
    private ExchangeOrderMapper exchangeOrderMapper;

    @Autowired
    private CheckAccountMapper checkAccountMapper;

    @Autowired
    private ExchangeRateMapper exchangeRateMapper;

    @Value("${exChangeUrl}")
    private String exChangeUrl;

    @Value("${rateUrl}")
    private String rateUrl;

    private final List<String> list = Arrays.asList("13333611437@qq.com", "jing.zhang@thingtrust.com");


    @Override
    public synchronized void insertExchangeOrder(final ExchangeOrderDTO exchangeOrderDTO) {
        final ExchangeOrder exchangeOrder = ExchangeOrder.builder()
                .currency(exchangeOrderDTO.getCurrency())
                .deviceAddress(exchangeOrderDTO.getDeviceAddress())
                .tttAddress(exchangeOrderDTO.getTttAddress())
                .toAddress(exchangeOrderDTO.getToAddress())
                .inviteCode(exchangeOrderDTO.getInviteCode())
                .payment(exchangeOrderDTO.getPayment())
                .states(StatesEnum.NON_PAYMENT.getCode())
                .build();
        this.exchangeOrderMapper.insertSelective(exchangeOrder);
    }

    @Override
    public Page getAllOrder(final int pageIndex, final int pageSize, final int status, final String condition) {
        final Page<ExchangeOrder> page = new Page<>(pageIndex, pageSize);
        final List<ExchangeOrder> exchangeOrderList = this.exchangeOrderMapper.selectByPage((pageIndex - 1) * pageSize, pageSize, status, condition);
        exchangeOrderList.stream()
                .forEach(exchangeOrder -> {
                    exchangeOrder.setCreTime(DateTimeUtils.formatDateTime(exchangeOrder.getCreateTime(), DateTimeUtils.secondPattern));
                });
        page.setResult(exchangeOrderList);
        page.setTotalCount(this.exchangeOrderMapper.countByOrder(status, condition));
        return page;
    }

    @Override
    public ResponseResult manualMoney(final Long id) {
        final ExchangeOrder exchangeOrder = this.exchangeOrderMapper.selectByPrimaryKey(id);

        if (exchangeOrder.getStates() == 1) {
            return ResponseResult.failure(StatesEnum.REQUEST_ERROR.getMsg());
        }
        final BigDecimal rate = this.getRate();
        exchangeOrder.setRate(rate);

        final BigDecimal checkBalance = this.checkBalance();
        if (checkBalance.compareTo(new BigDecimal(-1)) == 0) {
            return ResponseResult.failure(StatesEnum.CHECK_BALANCE_ERROR.getMsg() + exchangeOrder);
        }

        final BigDecimal quantity = exchangeOrder.getReceipt().divide(exchangeOrder.getRate(), 0, BigDecimal.ROUND_HALF_DOWN);
        if (checkBalance.compareTo(quantity) != 1) {
            exchangeOrder.setStates(StatesEnum.NOT_ENOUGH.getCode());
            this.exchangeOrderMapper.updateByPrimaryKeySelective(exchangeOrder);
            return ResponseResult.failure(StatesEnum.NOT_ENOUGH_MONEY.getMsg() + exchangeOrder);
        }
        exchangeOrder.setQuantity(quantity);

        final String url = this.exChangeUrl + "/payToAddress";
        final Map<String, String> param = new HashMap<>();
        param.put("address", exchangeOrder.getTttAddress());
        param.put("amount", exchangeOrder.getQuantity().toString());

        final String body = OkHttpUtils.get(url, param);
        if (body == null) {
            return ResponseResult.failure(StatesEnum.PAY_ADDRESS_FAILE.getMsg() + exchangeOrder);
        }

        final JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
        final int code = (int) jsonObject.get("code");
        if (code == StatesEnum.REQUEST_OK.getCode()) {
            exchangeOrder.setStates(StatesEnum.COMPLETE.getCode());
        } else {
            exchangeOrder.setStates(StatesEnum.TRANSFER_FAILE.getCode());
            this.exchangeOrderMapper.updateByPrimaryKeySelective(exchangeOrder);
            return ResponseResult.failure(body + StatesEnum.TRANSFER_FAILE.getMsg() + exchangeOrder);
        }

        this.exchangeOrderMapper.updateByPrimaryKeySelective(exchangeOrder);
        this.addRecord(exchangeOrder);

        final String postTransferResult = this.postTransferResult(exchangeOrder);
        if (postTransferResult == null) {
            exchangeOrder.setStates(StatesEnum.PUSH_FAILE.getCode());
            this.exchangeOrderMapper.updateByPrimaryKeySelective(exchangeOrder);
            return ResponseResult.failure(StatesEnum.PUSH_FAILE.getMsg() + exchangeOrder);
        }

        return ResponseResult.success(postTransferResult);
    }

    private String postTransferResult(final ExchangeOrder exchangeOrder) {
        final String deviceUrl = this.exChangeUrl + "/getTransferResult";
        final HashMap<String, String> params = new HashMap<>();
        params.put("from_address", exchangeOrder.getDeviceAddress());
        params.put("rate", exchangeOrder.getRate().toString());
        params.put("amount", exchangeOrder.getQuantity().toString());
        params.put("receipt", exchangeOrder.getReceipt().toString());
        final String deciveBody = OkHttpUtils.rpcRequestBodyPost(deviceUrl, params);
        return deciveBody;
    }

    @Override
    public BigDecimal checkBalance() {
        final String url = this.exChangeUrl + "/getWalletBalance";
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
        final List<String> columns = Arrays.asList("购买币种", "购买数量", "支付方式", "收到BTC数量", "收款BTC地址", "钱包地址", "汇率", "状态", "邀请码", "设备码", "创建时间");
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(StringUtils.join(columns, ","));
        final List<ExchangeOrder> exchangeOrderList = this.exchangeOrderMapper.selectByExample(null);
        exchangeOrderList.stream().forEach(exchangeOrder -> {
            stringBuilder.append("\r")
                    .append(exchangeOrder.getCurrency()).append(",")
                    .append(exchangeOrder.getQuantity()).append(",")
                    .append(exchangeOrder.getPayment()).append(",")
                    .append(exchangeOrder.getReceipt()).append(",")
                    .append(exchangeOrder.getToAddress()).append(",")
                    .append(exchangeOrder.getTttAddress()).append(",")
                    .append(exchangeOrder.getRate()).append(",")
                    .append(StatesUtils.getStates(exchangeOrder.getStates())).append(",")
                    .append(exchangeOrder.getInviteCode()).append(",")
                    .append(exchangeOrder.getDeviceAddress()).append(",")
                    .append(exchangeOrder.getCreateTime().toString().replaceAll("T", " "));
        });
        return stringBuilder.toString();
    }


    @Override
    public BigDecimal getRate() {
        final Map<String, String> map = new HashMap<>();
        map.put("coin", "ttt_btc");
        final String body = OkHttpUtils.get(this.rateUrl, map);
        final JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
        final int code = (int) jsonObject.get("code");
        if (code == 0) {
            return new BigDecimal(jsonObject.getJSONObject("data").get("last").toString());
        }
        this.sendExceptionMail(StatesEnum.RATE_CONNECT_ERROR.getMsg());
        final ExchangeRate exchangeRate = this.exchangeRateMapper.selectByPrimaryKey(3);
        return exchangeRate.getRate();
    }

    @Override
    public ConfirmBalanceDTO confirmBalance(final Long id) {
        final BigDecimal checkBalance = this.checkBalance();
        final ExchangeOrder exchangeOrder = this.exchangeOrderMapper.selectByPrimaryKey(id);
        final ConfirmBalanceDTO confirmBalanceDTO = ConfirmBalanceDTO.builder()
                .balance(checkBalance)
                .quantity(exchangeOrder.getQuantity())
                .states(StatesEnum.NOT_CAN.getMsg())
                .build();
        if (checkBalance.compareTo(exchangeOrder.getQuantity()) == 1) {
            confirmBalanceDTO.setStates(StatesEnum.CAN.getMsg());
        }
        return confirmBalanceDTO;
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
        final ExchangeEmailOrderDTO exchangeEmailOrderDTO = ExchangeEmailOrderDTO.builder()
                .state(StatesUtils.getStates(exchangeOrder.getStates()))
                .build();
        BeanUtils.copyProperties(exchangeOrder, exchangeEmailOrderDTO);
        this.list.stream().forEach(email -> {
            final SendingPool pool = SendingPool.getInstance();
            pool.addThread(new Sending(email, "TrustNote email", "有订单未处理" + exchangeEmailOrderDTO));
        });
    }

    @Override
    public void sendExceptionMail(final String msg) {
        this.list.stream().forEach(email -> {
            final SendingPool pool = SendingPool.getInstance();
            pool.addThread(new Sending(email, "TrustNote email", "有订单有异常" + msg));
        });
    }

}
