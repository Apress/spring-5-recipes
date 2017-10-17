package com.apress.springrecipes.post;

import org.springframework.kafka.annotation.KafkaListener;

public class MailListener {

    @KafkaListener(topics = "mails")
    public void displayMail(String mail) {
        System.out.println(" Received: " + mail);
    }
}
