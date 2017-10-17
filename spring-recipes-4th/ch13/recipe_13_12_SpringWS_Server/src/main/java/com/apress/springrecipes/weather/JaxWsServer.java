package com.apress.springrecipes.weather;

import java.io.IOException;

import org.springframework.context.support.GenericXmlApplicationContext;

public class JaxWsServer {

    public static void main(String[] args) throws IOException {
        new GenericXmlApplicationContext("appContext.xml");
    }
}
