package com.apress.springrecipes.caching;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import java.math.BigDecimal;

/**
 * Created by marten on 15-08-14.
 */
public class PlainCachingCalculationService implements CalculationService {

    private final Ehcache cache;

    public PlainCachingCalculationService(Ehcache cache) {
        this.cache = cache;
    }

    @Override
    public BigDecimal heavyCalculation(BigDecimal base, int power) {
        String key = base +"^"+power;
        Element result = cache.get(key);
        if (result != null) {
            return (BigDecimal) result.getObjectValue();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
        BigDecimal calculatedResult = base.pow(power);
        cache.putIfAbsent(new Element(key, calculatedResult));
        return calculatedResult;
    }
}
