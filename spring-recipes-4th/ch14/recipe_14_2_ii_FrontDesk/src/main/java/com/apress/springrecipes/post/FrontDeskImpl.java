package com.apress.springrecipes.post;

import org.springframework.jms.core.support.JmsGatewaySupport;

public class FrontDeskImpl extends JmsGatewaySupport implements FrontDesk {

    public void sendMail(final Mail mail) {
        getJmsTemplate().convertAndSend(mail);
    }
}
