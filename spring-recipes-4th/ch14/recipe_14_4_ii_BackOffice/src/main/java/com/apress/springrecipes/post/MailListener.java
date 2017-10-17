package com.apress.springrecipes.post;

import java.util.Map;

import org.springframework.jms.annotation.JmsListener;

public class MailListener {

    @JmsListener(destination = "mail.queue")
    public void displayMail(Map<String, Object> map) {
        Mail mail = new Mail();
        mail.setMailId((String) map.get("mailId"));
        mail.setCountry((String) map.get("country"));
        mail.setWeight((Double) map.get("weight"));
        System.out.println("Mail #" + mail.getMailId() + " received");
    }
}
