package org.trustnote.activity.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhuxl
 */
public class ZxlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {
        final SavedRequest savedRequest
                = this.requestCache.getRequest(request, response);

        if (savedRequest == null) {
            this.clearAuthenticationAttributes(request);
            return;
        }
        final String targetUrlParam = this.getTargetUrlParameter();
        if (this.isAlwaysUseDefaultTargetUrl()
                || (targetUrlParam != null
                && StringUtils.hasText(request.getParameter(targetUrlParam)))) {
            this.requestCache.removeRequest(request, response);
            this.clearAuthenticationAttributes(request);
            return;
        }

        this.clearAuthenticationAttributes(request);
    }

    public void setRequestCache(final RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}
