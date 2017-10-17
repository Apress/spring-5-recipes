package com.apress.springrecipes.caching;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

/**
 * Created by marten on 18-08-14.
 */
public class CustomKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return params[0] + "^" + params[1];
    }
}
