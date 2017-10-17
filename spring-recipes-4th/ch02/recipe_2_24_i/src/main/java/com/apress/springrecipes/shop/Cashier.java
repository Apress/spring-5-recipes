package com.apress.springrecipes.shop;

import java.io.IOException;
import java.util.Date;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;


public class Cashier implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    public void setApplicationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void checkout(ShoppingCart cart) throws IOException {
        CheckoutEvent event = new CheckoutEvent(cart, new Date());
        applicationEventPublisher.publishEvent(event);
    }
        

}
