package com.apress.springrecipes.springintegration;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(IntegrationConfiguration.class);

        System.out.println("Press [Enter] to close.");
        System.in.read();
        context.close();

    }
}
