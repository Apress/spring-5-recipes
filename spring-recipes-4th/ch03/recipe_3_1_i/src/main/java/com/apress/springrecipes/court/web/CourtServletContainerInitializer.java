package com.apress.springrecipes.court.web;

import com.apress.springrecipes.court.config.CourtConfiguration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

/**
 * {@link ServletContainerInitializer} implementation to bootstrap the {@link DispatcherServlet}.
 */
public class CourtServletContainerInitializer implements ServletContainerInitializer {

    public static final String MSG = "Starting Court Web Application";

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

        System.out.println(MSG);

        ctx.log(MSG);

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(CourtConfiguration.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);

        ServletRegistration.Dynamic courtRegistration = ctx.addServlet("court", dispatcherServlet);
        courtRegistration.setLoadOnStartup(1);
        courtRegistration.addMapping("/");


    }
}
