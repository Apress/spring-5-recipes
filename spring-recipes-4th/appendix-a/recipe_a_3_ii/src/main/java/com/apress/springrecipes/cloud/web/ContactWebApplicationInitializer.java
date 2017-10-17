package com.apress.springrecipes.cloud.web;

import com.apress.springrecipes.cloud.config.ContactConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by marten on 06-10-14.
 */
public class ContactWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {ContactConfiguration.class, ContactWebConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"} ;
    }
}
