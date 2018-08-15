package org.trustnote.activity.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.utils.Result;

import java.util.List;

public class ErrorsUtils {

    public static Result errors(final Errors errors) {
        final Result result = new Result();
        if (errors.hasErrors()) {
            final List<ObjectError> errorList = errors.getAllErrors();
            for (final ObjectError e : errorList) {
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg(e.getDefaultMessage());
                return result;
            }
        }
        return null;
    }
}
