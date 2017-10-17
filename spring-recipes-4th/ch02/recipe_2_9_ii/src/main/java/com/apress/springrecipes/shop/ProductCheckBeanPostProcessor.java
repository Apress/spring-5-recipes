package com.apress.springrecipes.shop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


public class ProductCheckBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof Product) {
            String productName = ((Product) bean).getName();
            System.out.println("In ProductCheckBeanPostProcessor.postProcessBeforeInitialization, processing Product: " + productName);
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof Product) {
            String productName = ((Product) bean).getName();
            System.out.println("In ProductCheckBeanPostProcessor.postProcessAfterInitialization, processing Product: " + productName);
        }
        return bean;
    }
}
