package com.apress.springrecipes.mobile.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.apress.springrecipes.mobile.web.config.MobileConfiguration;

public class MobileApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { MobileConfiguration.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
