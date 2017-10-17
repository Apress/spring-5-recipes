package com.apress.springrecipes.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext("beans.xml");

        HelloWorld helloWorld = context.getBean(HelloWorld.class);
        helloWorld.hello();
    }
}
