package org.trustnote.activity.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trustnote.activity.common.dto.DepositLockDTO;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.DepositService;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

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

    /**
     * 查询付款
     *
     * @param address
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryPayed", method = RequestMethod.GET)
    public String queryBanlance(final String address, final HttpServletResponse response) {
        DepositController.logger.info("paramers: {}", address);
        final Result result = new Result();
        if (StringUtils.isBlank(address) || StringUtils.isEmpty(address)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg(ResultEnum.BAD_REQUEST.appendMsg("The address is mandatory."));
            return result.getString(result);
        }
        final boolean querybalance = this.depositService.querybalance(address);
        response.setStatus(HttpServletResponse.SC_OK);
        result.setCode(ResultEnum.OK.getCode());
        result.setEntity(querybalance);
        return result.getString(result);

    }

    /**
     * 查询所有
     *
     * @param
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryAll(final int startIndex, final int pageSize, final HttpServletResponse response) {
        final Result result = new Result();
        final Page<DepositLockDTO> depositLockDTOPage = this.depositService.queryAll(startIndex, pageSize);
        response.setStatus(HttpServletResponse.SC_OK);
        result.setCode(ResultEnum.OK.getCode());
        result.setEntity(depositLockDTOPage);
        return result.getString(result);

    }


}
