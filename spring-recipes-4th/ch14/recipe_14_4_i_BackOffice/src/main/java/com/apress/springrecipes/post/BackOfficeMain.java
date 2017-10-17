package com.apress.springrecipes.post;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BackOfficeMain {

    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("beans-back.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext("com.apress.springrecipes.post.config");
    }
}
