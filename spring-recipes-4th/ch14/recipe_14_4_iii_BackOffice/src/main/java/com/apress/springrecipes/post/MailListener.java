package com.apress.springrecipes.post;

import org.springframework.jms.annotation.JmsListener;

public class MailListener {

    @JmsListener(destination = "mail.queue")
    public void displayMail(Mail mail) {
        System.out.println("Mail #" + mail.getMailId() + " received");
    }
}
