package com.apress.springrecipes.mobile.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class DeviceResolverRequestFilter extends OncePerRequestFilter {

    private static final String CURRENT_DEVICE_ATTRIBUTE = "currentDevice";

    private static final String DEVICE_MOBILE = "MOBILE";
    private static final String DEVICE_TABLET = "TABLET";
    private static final String DEVICE_NORMAL = "NORMAL";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String userAgent = request.getHeader("User-Agent");
        String device = DEVICE_NORMAL;

        if (StringUtils.hasText(userAgent)) {
            userAgent = userAgent.toLowerCase();
            if (userAgent.contains("android")) {
                device = userAgent.contains("mobile") ? DEVICE_NORMAL : DEVICE_TABLET;
            } else if (userAgent.contains("ipad") || userAgent.contains("playbook") || userAgent.contains("kindle")) {
                device = DEVICE_TABLET;
            } else if (userAgent.contains("mobil") || userAgent.contains("ipod") || userAgent.contains("nintendo DS")) {
                device = DEVICE_MOBILE;
            }
        }
        // Set the detected request as an attribute to make it available further down the chain.
        request.setAttribute(CURRENT_DEVICE_ATTRIBUTE, device);
        filterChain.doFilter(request, response);
    }
}
