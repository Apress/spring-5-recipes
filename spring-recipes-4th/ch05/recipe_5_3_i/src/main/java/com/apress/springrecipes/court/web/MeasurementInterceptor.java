package com.apress.springrecipes.court.web;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MeasurementInterceptor implements AsyncHandlerInterceptor {


    public static final String START_TIME = "startTime";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getAttribute(START_TIME) == null) {
            request.setAttribute(START_TIME, System.currentTimeMillis());
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        long startTime = (Long) request.getAttribute(START_TIME);
        request.removeAttribute(START_TIME);
        long endTime = System.currentTimeMillis();
        System.out.println("Response-Processing-Time: " + (endTime - startTime) + "ms.");
        System.out.println("Response-Processing-Thread: " + Thread.currentThread().getName());
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long startTime = (Long) request.getAttribute(START_TIME);
        request.setAttribute(START_TIME, System.currentTimeMillis());
        long endTime = System.currentTimeMillis();

        System.out.println("Request-Processing-Time: " + (endTime - startTime) + "ms.");
        System.out.println("Request-Processing-Thread: " + Thread.currentThread().getName());
    }
}
