package com.apress.springrecipes.springintegration;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ServerMain {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(IntegrationConfiguration.class);

        System.out.println("Press [ENTER] to stop.");
        System.in.read();

        context.close();


    }
}
