package com.apress.springrecipes.shop;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class CheckoutEvent extends ApplicationEvent {

    private final ShoppingCart cart;
    private final Date time;
    
    public CheckoutEvent(ShoppingCart cart, Date time) {
        super(cart);
        this.cart=cart;
        this.time = time;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public Date getTime() {
        return this.time;
    }
}
