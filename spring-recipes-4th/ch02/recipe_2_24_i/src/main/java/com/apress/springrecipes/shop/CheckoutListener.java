package com.apress.springrecipes.shop;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CheckoutListener implements ApplicationListener<CheckoutEvent> {

    public void onApplicationEvent(CheckoutEvent event) {
        // Do anything you like with the checkout time
        System.out.println("Checkout event [" + event.getTime() + "]");
    }
}
