package com.apress.springrecipes.springintegration;

public class Operands {
    private final Number a;
    private final Number b;

    public Operands(Number a, Number b) {
        this.a = a;
        this.b = b;
    }

    public Number getA() {
        return a;
    }

    public Number getB() {
        return b;
    }

    @Override
    public String toString() {

        return String.format("Operands [a=%d,b=%d]",a,b);
    }
}
