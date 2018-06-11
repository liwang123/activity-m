package org.trustnote.activity.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.TickerService;
import org.trustnote.activity.stereotype.Frequency;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import static org.trustnote.activity.controller.ResultUtil.universalExceptionReturn;

/**
 * @author zhuxl
 */
@Frequency(name = "activity", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/activity")
public class TickerController {
    private static final Logger logger = LogManager.getLogger(TickerController.class);

    @Resource
    private TickerService tickerService;

    @ResponseBody
    @RequestMapping(value = "/realTimePrice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryBySelective(@RequestParam(value = "coin") final String coin,
                                   @RequestParam(value = "language", required = false, defaultValue = "en") final String language,
                                   final HttpServletResponse response) {
        TickerController.logger.info("coid {}", coin);
        final Result result = new Result();
        try {
            return result.getString(result);
        } catch (final Exception e) {
            return universalExceptionReturn(TickerController.logger, e, response, result);
        }
    }
}
