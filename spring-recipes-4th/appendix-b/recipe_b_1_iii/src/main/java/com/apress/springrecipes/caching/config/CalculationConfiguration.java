package com.apress.springrecipes.caching.config;

import com.apress.springrecipes.caching.CalculationService;
import com.apress.springrecipes.caching.PlainCachingCalculationService;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by marten on 15-08-14.
 */
@Configuration
public class CalculationConfiguration {

    @Bean
    public CacheManager cacheManager() {
        return CacheManager.getInstance();
    }

    @Bean
    public CalculationService calculationService() {
        Ehcache cache = cacheManager().getCache("calculations");
        return new PlainCachingCalculationService(cache);
    }
}
