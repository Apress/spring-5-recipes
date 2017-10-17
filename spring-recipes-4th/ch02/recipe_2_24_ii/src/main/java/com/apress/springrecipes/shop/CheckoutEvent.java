package com.apress.springrecipes.shop;

import java.util.Date;

public class CheckoutEvent {

    private final ShoppingCart cart;
    private final Date time;
    
    public CheckoutEvent(ShoppingCart cart, Date time) {
        this.cart=cart;
        this.time = time;
    }

    public ShoppingCart getCart() {
        return this.cart;
    }

    public Date getTime() {
        return time;
    }
}
