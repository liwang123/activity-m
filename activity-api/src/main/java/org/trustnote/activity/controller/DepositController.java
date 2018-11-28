package org.trustnote.activity.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.DepositService;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
//@Frequency(name = "coin", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/deposit")
public class DepositController {
    private static final Logger logger = LogManager.getLogger(DepositController.class);
    @Autowired
    private DepositService depositService;

    /**
     * 押金锁仓
     *
     * @param address
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertMessage", method = RequestMethod.POST)
    public String queryCoin(final String address, final int status, final HttpServletResponse response) {
        DepositController.logger.info("paramers: {}", address);
        final Result result = new Result();
        if (StringUtils.isBlank(address) || StringUtils.isEmpty(address)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg(ResultEnum.BAD_REQUEST.appendMsg("The address is mandatory."));
            return result.getString(result);
        }
        final String addressEntity = this.depositService.insert(address, status);
        response.setStatus(HttpServletResponse.SC_OK);
        result.setCode(ResultEnum.OK.getCode());
        result.setEntity(addressEntity);
        return result.getString(result);

    }


}
