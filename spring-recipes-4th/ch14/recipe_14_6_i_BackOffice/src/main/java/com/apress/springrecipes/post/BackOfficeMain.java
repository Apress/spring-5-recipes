package com.apress.springrecipes.post;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.post.config.BackOfficeConfiguration;

public class BackOfficeMain {

    public static void main(String[] args) throws Exception {
            ApplicationContext context =
                    new AnnotationConfigApplicationContext(BackOfficeConfiguration.class);

            BackOffice backOffice = context.getBean(BackOffice.class);
            backOffice.receiveMail();

            System.in.read();
    }
}
