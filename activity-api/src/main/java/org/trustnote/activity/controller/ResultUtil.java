package org.trustnote.activity.controller;

import org.apache.logging.log4j.Logger;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.utils.Result;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuxl 17-12-28
 * @since v0.3
 */
public class ResultUtil {
    public static String universalExceptionReturn(final Logger logger, final Exception e, final HttpServletResponse response, final Result result) {
        logger.error("exception {}", e);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.setCode(ResultEnum.INTERNAL_SERVER_ERROR.getCode());
        result.setMsg(ResultEnum.INTERNAL_SERVER_ERROR.appendMsg("Internal Server Error."));
        return result.getString(result);
    }

    public static String universalThrowableReturn(final Logger logger, final Throwable t, final HttpServletResponse response, final Result result) {
        logger.error("exception {}", t.getMessage());
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.setCode(ResultEnum.INTERNAL_SERVER_ERROR.getCode());
        result.setMsg(ResultEnum.INTERNAL_SERVER_ERROR.appendMsg("Internal Server Error."));
        return result.getString(result);
    }

    public static String universalJSONExceptionReturn(final Logger logger, final Exception e, final HttpServletResponse response, final Result result) {
        logger.error("exception {}", e);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.setCode(ResultEnum.JSON_PARSE_ERROR.getCode());
        result.setMsg(ResultEnum.JSON_PARSE_ERROR.getMsg());
        return result.getString(result);
    }

    public static String universalBlankReturn(final HttpServletResponse response, final Result result) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        result.setCode(ResultEnum.BAD_REQUEST.getCode());
        result.setMsg(ResultEnum.BAD_REQUEST.appendMsg("Please send corrected parameters."));
        return result.getString(result);
    }

    public static String universalZxlExceptionReturn(final Logger logger, final Exception e, final HttpServletResponse response, final Result result) {
        logger.error("exception {}", e);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.setCode(ResultEnum.INTERNAL_SERVER_ERROR.getCode());
        result.setMsg(e.getMessage());
        return result.getString(result);
    }

}
