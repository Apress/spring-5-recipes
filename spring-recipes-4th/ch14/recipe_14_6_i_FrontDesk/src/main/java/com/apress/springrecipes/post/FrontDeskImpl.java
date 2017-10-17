package com.apress.springrecipes.post;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.springframework.scheduling.annotation.Scheduled;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class FrontDeskImpl implements FrontDesk {

    private static final String QUEUE_NAME = "mail.queue";

    public void sendMail(final Mail mail) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setPort(5672);

        Connection connection = null;
        Channel channel = null;
        try {

            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String message = new ObjectMapper().writeValueAsString(mail);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println("Send: " + message);
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException | TimeoutException e) {
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException e) {
                }
            }
        }
    }

    @Scheduled(initialDelay = 175, fixedDelay = 225)
    public void sendMailTrigger() {
        Random rnd = new Random();
        int countries = Locale.getISOCountries().length;
        String country = Locale.getISOCountries()[rnd.nextInt(countries)];

        sendMail(new Mail(String.valueOf(System.currentTimeMillis()), country, rnd.nextDouble()));
    }
}
