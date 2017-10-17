package com.apress.springrecipes.post;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BackOfficeMain {

    public static void main(String[] args) {
        ApplicationContext context = 
            new GenericXmlApplicationContext("beans-back.xml");
    }
}
