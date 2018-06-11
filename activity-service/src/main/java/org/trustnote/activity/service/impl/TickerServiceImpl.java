package org.trustnote.activity.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.example.TickerExample;
import org.trustnote.activity.common.pojo.Ticker;
import org.trustnote.activity.service.iface.TickerService;
import org.trustnote.activity.skeleton.mybatis.mapper.TickerMapper;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * 〈〉
 *
 * @author WangYu
 * @create 2018/5/29
 * @since 1.0.0
 */
@Service
public class TickerServiceImpl implements TickerService {

    @Autowired
    private TickerMapper tickerMapper;

    @Override
    public void saveBitZTicker(String currencyPair) {
        String url = "http://www.bit-z.com/api_v1/ticker?coin="+currencyPair;
        HttpGet request = new HttpGet(url);
        // 获取当前客户端对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try(CloseableHttpResponse httpResponse = httpClient.execute(request)) {

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String json = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                JSONObject jsonObject = (JSONObject) JSONObject.parse(json);
                Ticker ticker = new Ticker();
                ticker.setLast(jsonObject.getBigDecimal("last"));
                ticker.setBuy(jsonObject.getBigDecimal("buy"));
                ticker.setSell(jsonObject.getBigDecimal("sell"));
                ticker.setHigh(jsonObject.getBigDecimal("high"));
                ticker.setLow(jsonObject.getBigDecimal("low"));
                ticker.setVol(jsonObject.getBigDecimal("vol"));

                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(jsonObject.getLong("date"));
                Date date = cal.getTime();
                ticker.setDate(date);

                if(currencyPair.equals("ttt_btc")){
                    ticker.setId(1L);
                }
                if(currencyPair.equals("ttt_eth")){
                    ticker.setId(2L);
                }
                if(currencyPair.equals("btc_dkkt")){
                    ticker.setId(3L);
                }
                if(currencyPair.equals("eth_dkkt")){
                    ticker.setId(4L);
                }
                ticker.setCurrency(currencyPair);
                tickerMapper.updateByPrimaryKey(ticker);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTicker(String currency, String language) {
        TickerExample example = new TickerExample();
        TickerExample.Criteria criteria = example.createCriteria();
        criteria.andCurrencyEqualTo(currency);
        example.setOrderByClause("id DESC");
        Ticker ticker = tickerMapper.selectByExampleReturnOne(example);

        if (ticker != null) {
            example = new TickerExample();
            TickerExample.Criteria criteriaDKKT = example.createCriteria();
            String dkkt = "eth_dkkt";
            if ("ttt_btc".equals(currency)) {
                dkkt = "btc_dkkt";
            }
            criteriaDKKT.andCurrencyEqualTo(dkkt);
            Ticker tickerDkkt = tickerMapper.selectByExampleReturnOne(example);
            if (tickerDkkt != null) {

            }
        }
        return null;
    }
}
