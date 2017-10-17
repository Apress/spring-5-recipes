package com.apress.springrecipes.post;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.post.config.FrontOfficeConfiguration;

public class FrontDeskMain {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context =
            new AnnotationConfigApplicationContext(FrontOfficeConfiguration.class);

        FrontDesk frontDesk = context.getBean(FrontDesk.class);
        frontDesk.sendMail(new Mail("1234", "US", 1.5));

        System.in.read();

        context.close();
    }


}
