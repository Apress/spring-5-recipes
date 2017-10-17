package com.apress.springrecipes.caching;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import java.math.BigDecimal;

/**
 * Created by marten on 15-08-14.
 */
public class Main {

    public static final void main(String[] args) throws Exception {
        CacheManager cacheManager = CacheManager.getInstance();
        Ehcache cache = cacheManager.getEhcache("calculations");
        CalculationService calculationService = new PlainCachingCalculationService(cache);
        for (int i = 0; i < 10 ;i++) {
            long start = System.currentTimeMillis();
            System.out.println(calculationService.heavyCalculation(BigDecimal.valueOf(2L), 16));
            long duration = System.currentTimeMillis() - start;
            System.out.println("Took: " + duration);
        }

        cacheManager.shutdown();
    }
}
