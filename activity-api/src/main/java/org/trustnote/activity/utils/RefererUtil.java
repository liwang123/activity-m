package org.trustnote.activity.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhuxl
 */
@Component
public class RefererUtil {
    @Value("${referer}")
    protected String referer;
}
