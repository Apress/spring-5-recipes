package com.apress.springrecipes.springintegration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {
       ApplicationContext applicationContext = new ClassPathXmlApplicationContext("integration-context.xml");

    }
}
