package org.trustnote.activity.security;

import org.springframework.security.access.ConfigAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhuxl
 */
public class ZxlConfigAttribute implements ConfigAttribute {
    private final HttpServletRequest httpServletRequest;

    public ZxlConfigAttribute(final HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public String getAttribute() {
        return null;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }
}
