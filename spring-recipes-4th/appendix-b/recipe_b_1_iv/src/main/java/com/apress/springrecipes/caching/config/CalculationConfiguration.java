package com.apress.springrecipes.caching.config;

import com.apress.springrecipes.caching.CalculationService;
import com.apress.springrecipes.caching.PlainCachingCalculationService;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by marten on 15-08-14.
 */
@Configuration
public class CalculationConfiguration {

    @Bean
    public EhCacheManagerFactoryBean cacheManager() {
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return factory;
    }

    @Bean
    public EhCacheFactoryBean calculationsCache() {
        EhCacheFactoryBean factory = new EhCacheFactoryBean();
        factory.setCacheManager(cacheManager().getObject());
        factory.setCacheName("calculations");
        return factory;
    }

    @Bean
    public CalculationService calculationService() {
        return new PlainCachingCalculationService(calculationsCache().getObject());
    }

}
