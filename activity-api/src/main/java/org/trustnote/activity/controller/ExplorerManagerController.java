package org.trustnote.activity.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.utils.ExcelExplorerManagerUtils;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.stereotype.Frequency;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.trustnote.activity.controller.ResultUtil.universalExceptionReturn;

/**
 * @author zhuxl
 */
@Frequency(name = "explorer", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/explorerManager")
public class ExplorerManagerController {
    private static final Logger logger = LogManager.getLogger(ExplorerManagerController.class);

    /**
     * @return
     */
    @RequestMapping(value = "listByDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String listByDay(@RequestParam final String amount, @RequestParam final String time, @RequestParam final int type, @RequestParam(required = false, defaultValue = "2") final int export, final HttpServletResponse resp) {
        ExplorerManagerController.logger.info("time: {} type: {} export: {}", time, type, export);
        final String url = "http://localhost:3000";
        String urlNameString = "";
        if (type == 1) {
            urlNameString = url + "/staticdetail?date=" + LocalDate.now().toString();
            if (StringUtils.isNotBlank(time)) {
                urlNameString = url + "/staticdetail?date=" + time;
            }
        }
        if (type == 2) {
            urlNameString = url + "/static?from_date=startTime&to_date=endTime";
            urlNameString = urlNameString.replace("startTime", "2010-04-17");
            urlNameString = urlNameString.replace("endTime", LocalDate.now().toString());
            ExplorerManagerController.logger.info("urlNameString{}" + urlNameString);
        }
        if (type == 3) {
            urlNameString = url + "/staticrealtime?amount=" + amount;
        }
        final Map<String, Object> re = new HashMap<>();
        try {
            // 根据地址获取请求
            final HttpGet request = new HttpGet(urlNameString);
            // 获取当前客户端对象
            final HttpClient httpClient = new DefaultHttpClient();
            // 通过请求对象获取响应对象
            final HttpResponse response = httpClient.execute(request);
            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                final String result = EntityUtils.toString(response.getEntity(), "utf-8");

                final JSONArray parse = (JSONArray) JSONArray.parse(result);
                final BigDecimal total = new BigDecimal("500000000000000");
                final BigDecimal mn = new BigDecimal("1000000");
                final NumberFormat percent = NumberFormat.getPercentInstance();
                percent.setMaximumFractionDigits(1);
                if (parse != null) {
                    if (2 == type) {
                        for (int i = 0; i < parse.size(); i++) {
                            final JSONObject jsonObject = parse.getJSONObject(i);
                            final BigDecimal sum = new BigDecimal(jsonObject.get("sum").toString());
                            jsonObject.put("amount", new BigDecimal(jsonObject.get("count").toString()).divide(mn));
                            jsonObject.put("proportion", percent.format(sum.divide(total, 3, BigDecimal.ROUND_DOWN).doubleValue()));
                            jsonObject.put("sum", sum.divide(mn));
                        }
                        re.put("entity", parse);
                    }else {
                        BigDecimal sum = new BigDecimal("0");
                        for (int i = 0; i < parse.size(); i++) {
                            final JSONObject jsonObject = parse.getJSONObject(i);
                            sum = sum.add(new BigDecimal(jsonObject.get("amount").toString()));
                            jsonObject.put("amount", new BigDecimal(jsonObject.get("amount").toString()).divide(mn));
                        }
                        final Map<String, Object> headResult = new HashMap<>();
                        headResult.put("proportion", percent.format(sum.divide(total, 3, BigDecimal.ROUND_DOWN).doubleValue()));
                        headResult.put("sum", sum.divide(mn));
                        re.put("head", headResult);
                        re.put("entity", parse);
                    }
                }
                if (export == 1) {
                    String execl = "";
                    final List<String> header = new ArrayList<>();
                    header.add("地址");
                    header.add("数量");
                    header.add("时间");
                    execl = ExcelExplorerManagerUtils.exportExcel("地址.xls", header, parse, 1, resp);
                    return execl;
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return JSONArray.toJSONString(re);
    }

    /**
     * 地址排名
     * @param address
     * @param response
     * @return
     */
    @RequestMapping(value = "rank", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String rank(@RequestParam final String address, final HttpServletResponse response) {
        ExplorerManagerController.logger.info("address: {}", address);
        final String url = "http://localhost:3000/rank?address=" + address.trim();
        ExplorerManagerController.logger.info("url: {}", url);
        final Result result = new Result();
        // 根据地址获取请求
        final HttpGet request = new HttpGet(url);
        // 获取当前客户端对象
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        try (final CloseableHttpResponse httpResponse = httpClient.execute(request)) {
            // 判断网络连接状态码是否正常(0--200都数正常)
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                final String json = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                final JSONObject jsonObject = (JSONObject) JSONObject.parse(json);
                final int errCode = Integer.parseInt(jsonObject.get("errCode").toString());
                if (1 ==  errCode) {
                    result.setCode(ResultEnum.BAD_REQUEST.getCode());
                    result.setMsg("address not found.");
                }else if (0 ==  errCode) {
                    result.setCode(ResultEnum.OK.getCode());
                    result.setMsg(ResultEnum.OK.getMsg());
                    result.setEntity(jsonObject.get("data"));
                }
            }
        } catch (final IOException e) {
            return universalExceptionReturn(ExplorerManagerController.logger, e, response, result);
        }
        return result.getString(result);
    }
}
