package com.apress.springrecipes.shop;
import java.text.DecimalFormat;

public abstract class Product {

    private String name;
    private double price;
    
    public Product() {}

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters and Setters
    public void setName(String name) { 
	this.name = name;
    }
    
    public void setPrice(double price) { 
	this.price = price;
    }

    public String getName() { 
	return name;
    }

    public double getPrice() { 
	return price;
    }

    public String toString() {
	DecimalFormat df = new DecimalFormat("#%");
        return name + " " + price;
    }
}
