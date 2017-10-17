package com.apress.springrecipes.post;

import javax.jms.MapMessage;

import org.springframework.jms.core.JmsTemplate;

public class FrontDeskImpl implements FrontDesk {

    private JmsTemplate jmsTemplate;


    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    public void sendMail(final Mail mail) {
        jmsTemplate.send(session -> {
            MapMessage message = session.createMapMessage();
            message.setString("mailId", mail.getMailId());
            message.setString("country", mail.getCountry());
            message.setDouble("weight", mail.getWeight());
            return message;
        });
    }
}
