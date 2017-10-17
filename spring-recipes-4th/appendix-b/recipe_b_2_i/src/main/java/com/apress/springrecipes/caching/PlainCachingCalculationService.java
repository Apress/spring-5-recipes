package com.apress.springrecipes.caching;

import org.springframework.cache.Cache;

import java.math.BigDecimal;

/**
 * Created by marten on 15-08-14.
 */
public class PlainCachingCalculationService implements CalculationService {

    private final Cache cache;

    public PlainCachingCalculationService(Cache cache) {
        this.cache = cache;
    }

    @Override
    public BigDecimal heavyCalculation(BigDecimal base, int power) {
        String key = base +"^"+power;
        BigDecimal result = cache.get(key, BigDecimal.class);
        if (result != null) {
            return result;
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}

        BigDecimal calculatedResult = base.pow(power);
        cache.put(key, calculatedResult);
        return calculatedResult;
    }
}
