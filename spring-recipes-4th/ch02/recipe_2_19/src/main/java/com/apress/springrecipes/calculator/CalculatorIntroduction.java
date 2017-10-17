package com.apress.springrecipes.calculator;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CalculatorIntroduction {

    @DeclareParents(
            value = "com.apress.springrecipes.calculator.ArithmeticCalculatorImpl",
            defaultImpl = MaxCalculatorImpl.class)
    public MaxCalculator maxCalculator;

    @DeclareParents(
            value = "com.apress.springrecipes.calculator.ArithmeticCalculatorImpl",
            defaultImpl = MinCalculatorImpl.class)
    public MinCalculator minCalculator;


    @DeclareParents(
            value = "com.apress.springrecipes.calculator.*CalculatorImpl",
            defaultImpl = CounterImpl.class)
    public Counter counter;

    @After("execution(* com.apress.springrecipes.calculator.*Calculator.*(..))"
            + " && this(counter)")
    public void increaseCount(Counter counter) {
        counter.increase();
    }

}
