package org.trustnote.activity.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhuxl
 */
@Service
public class ZxlSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Override
    public Collection<ConfigAttribute> getAttributes(final Object o) throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation) o).getRequest();
        final Set<ConfigAttribute> allAttributes = new HashSet<>();
        final ConfigAttribute configAttribute = new ZxlConfigAttribute(request);
        allAttributes.add(configAttribute);
        return allAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(final Class<?> aClass) {
        return true;
    }
}
