package com.apress.springrecipes.social;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.apress.springrecipes.social.config.SocialConfig;
import com.apress.springrecipes.social.config.WebConfig;

/**
 * Created by marten on 14-07-14.
 */
public class SocialWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SocialConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfig.class, };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

}
