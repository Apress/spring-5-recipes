package com.apress.springrecipes.reactive.court;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.ipc.netty.http.server.HttpServer;

public class ReactorNettyBootstrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebFluxConfiguration.class);
        HttpHandler handler =
                WebHttpHandlerBuilder.applicationContext(context).build();

        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);

        HttpServer.create("localhost", 8090)
                .newHandler(adapter).block();
    }
}
