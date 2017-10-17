package com.apress.springrecipes.springintegration;


/**
 * simple example designed to demonstrate adding over queues! Horribly inefficient, but it demonstrates a point.
 */
public class AdditionService {

    public Number add(Operands ops) {
        return (ops.getA().floatValue() + ops.getB().floatValue());
    }

}
