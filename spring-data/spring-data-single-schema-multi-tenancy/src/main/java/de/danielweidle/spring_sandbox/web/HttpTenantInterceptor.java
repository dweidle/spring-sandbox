package de.danielweidle.spring_sandbox.web;

import de.danielweidle.spring_sandbox.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

@RequiredArgsConstructor
public class HttpTenantInterceptor implements WebRequestInterceptor {

    @Override
    public void preHandle(WebRequest request) throws Exception {
        SecurityContext context = SecurityContextHolder.getContext();
        String tenant = request.getHeader("X-TENANT");
        if(tenant != null) {
            TenantContext.setTenant(tenant);
        } else {
            // TODO: Some meaningful exception should be thrown that is handled via global exception handler!
            throw new RuntimeException("Tenant Missing");
        }
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
    }
}


