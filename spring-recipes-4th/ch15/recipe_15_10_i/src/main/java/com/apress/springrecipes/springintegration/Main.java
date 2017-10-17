package com.apress.springrecipes.springintegration;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.messaging.MessageChannel;


public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AdditionConfiguration.class);
        MessageChannel request = ctx.getBean("request", MessageChannel.class);
        MessageChannel response = ctx.getBean("response", MessageChannel.class);

        SimpleMessagingGateway msgGateway = new SimpleMessagingGateway();
        msgGateway.setRequestChannel(request);
        msgGateway.setReplyChannel(response);
        msgGateway.setBeanFactory(ctx);
        msgGateway.afterPropertiesSet();
        msgGateway.start();

        Number result = msgGateway.convertSendAndReceive(new Operands(22, 4));

        System.out.printf("Result: %f%n", result.floatValue());

        ctx.close();

    }
}
