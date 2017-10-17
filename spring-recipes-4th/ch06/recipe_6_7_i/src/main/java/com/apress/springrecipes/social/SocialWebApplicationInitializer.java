package com.apress.springrecipes.social;

import javax.servlet.Filter;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.apress.springrecipes.social.config.SecurityConfig;
import com.apress.springrecipes.social.config.SocialConfig;
import com.apress.springrecipes.social.config.WebConfig;

/**
 * Created by marten on 14-07-14.
 */
public class SocialWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SecurityConfig.class, SocialConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfig.class, };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy();
        springSecurityFilterChain.setTargetBeanName("springSecurityFilterChain");
        return new Filter[]{springSecurityFilterChain};
    }
}
