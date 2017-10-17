package com.apress.springrecipes.calculator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ComplexFormatter {

    @Value("(a + bi)")
    private String pattern;

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String format(Complex complex) {
        return pattern.replaceAll("a", Integer.toString(complex.getReal()))
                .replaceAll("b", Integer.toString(complex.getImaginary()));
    }
}
