package com.apress.springrecipes.weather.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


public class Initializer implements WebApplicationInitializer {
    public void onStartup(ServletContext container)
            throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WeatherConfig.class);

        container.addListener(new ContextLoaderListener(context));

        ServletRegistration.Dynamic cxf = container.addServlet("cxf", new CXFServlet());
        cxf.setLoadOnStartup(1);
        cxf.addMapping("/*");
    }
}
