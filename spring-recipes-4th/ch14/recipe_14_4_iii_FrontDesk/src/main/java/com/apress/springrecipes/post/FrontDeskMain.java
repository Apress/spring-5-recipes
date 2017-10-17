package com.apress.springrecipes.post;

import java.util.Locale;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.post.config.FrontOfficeConfiguration;

public class FrontDeskMain {

    public static void main(String[] args) {
        ApplicationContext context = 
            new AnnotationConfigApplicationContext(FrontOfficeConfiguration.class);

        final Random rnd = new Random();
        FrontDesk frontDesk = (FrontDesk) context.getBean("frontDesk");

        int countries = Locale.getAvailableLocales().length;

        while (true) {

            String mailId = String.valueOf(rnd.nextInt());
            String country = Locale.getAvailableLocales()[rnd.nextInt(countries)].getCountry();
            double weight = rnd.nextDouble();

            frontDesk.sendMail(new Mail(mailId, country, weight));

            try {
                Thread.sleep(rnd.nextInt(500));
            } catch (InterruptedException e) {
            }
        }

    }
}
