package com.apress.springrecipes.shop;

public class Disc extends Product {

    private int capacity;

    public Disc() {
        super();
    }

    public Disc(String name, double price, double discount) {
        super(name, price, discount);
    }

    public int getCapacity() {
        return capacity;
    }

    // Getters and Setters
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
