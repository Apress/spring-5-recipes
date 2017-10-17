package com.apress.springrecipes.shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.shop.config.ShopConfiguration;

public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(ShopConfiguration.class);

        Product aaa = context.getBean("aaa", Product.class);
        Product cdrw = context.getBean("cdrw", Product.class);
        System.out.println(aaa);
        System.out.println(cdrw);
    }
}
