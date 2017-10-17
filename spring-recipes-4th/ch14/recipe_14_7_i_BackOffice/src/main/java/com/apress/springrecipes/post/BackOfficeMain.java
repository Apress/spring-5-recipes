package com.apress.springrecipes.post;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.post.config.BackOfficeConfiguration;

public class BackOfficeMain {

    public static void main(String[] args) {
            new AnnotationConfigApplicationContext(BackOfficeConfiguration.class);
    }
}
