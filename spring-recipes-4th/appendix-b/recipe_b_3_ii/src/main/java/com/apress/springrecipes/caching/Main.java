package com.apress.springrecipes.caching;

import com.apress.springrecipes.caching.config.CalculationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.math.BigDecimal;

/**
 * Created by marten on 15-08-14.
 */
public class Main {

    public static final void main(String[] args) throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(CalculationConfiguration.class);
        CalculationService calculationService = context.getBean(CalculationService.class);

        System.out.println("Service: " + calculationService);

        for (int i = 0; i < 10 ;i++) {
            long start = System.currentTimeMillis();
            System.out.println(calculationService.heavyCalculation(BigDecimal.valueOf(2L), 16));
            long duration = System.currentTimeMillis() - start;
            System.out.println("Took: " + duration);
        }

        ((AbstractApplicationContext) context).close();
    }
}
