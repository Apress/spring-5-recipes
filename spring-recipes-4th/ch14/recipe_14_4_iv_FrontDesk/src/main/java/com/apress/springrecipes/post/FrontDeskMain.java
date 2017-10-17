package com.apress.springrecipes.post;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class FrontDeskMain {

    public static void main(String[] args) {
        ApplicationContext context = 
            new GenericXmlApplicationContext("beans-front.xml");

        FrontDesk frontDesk = (FrontDesk) context.getBean("frontDesk");
        frontDesk.sendMail(new Mail("1234", "US", 1.5));
    }
}
