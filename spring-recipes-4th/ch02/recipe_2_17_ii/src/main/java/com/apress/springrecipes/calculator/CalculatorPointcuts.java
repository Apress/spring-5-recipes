package com.apress.springrecipes.calculator;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class CalculatorPointcuts {

    @Pointcut("within(com.apress.springrecipes.calculator.LoggingRequired)")
    public void loggingOperation() {
    }

}
