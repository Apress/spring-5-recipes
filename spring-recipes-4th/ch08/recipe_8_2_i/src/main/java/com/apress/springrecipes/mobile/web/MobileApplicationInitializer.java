package com.apress.springrecipes.mobile.web;

import javax.servlet.Filter;

import org.springframework.mobile.device.DeviceResolverRequestFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.apress.springrecipes.mobile.web.config.MobileConfiguration;

/**
 * Created by marten on 23-06-14.
 */
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
    protected Filter[] getServletFilters() {
        return new Filter[] {new DeviceResolverRequestFilter()};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
