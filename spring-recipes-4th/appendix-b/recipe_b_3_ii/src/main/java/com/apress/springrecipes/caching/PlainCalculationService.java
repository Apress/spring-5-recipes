package com.apress.springrecipes.caching;

import org.springframework.cache.annotation.Cacheable;

import java.math.BigDecimal;

/**
 * Created by marten on 15-08-14.
 */
public class PlainCalculationService implements CalculationService {

    @Override
    @Cacheable("calculations")
    public BigDecimal heavyCalculation(BigDecimal base, int power) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
        return base.pow(power);
    }
}
