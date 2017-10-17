package com.apress.springrecipes.post;

import java.util.Map;

import org.springframework.jms.core.support.JmsGatewaySupport;

public class BackOfficeImpl extends JmsGatewaySupport implements BackOffice {

    public Mail receiveMail() {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) getJmsTemplate().receiveAndConvert();
        Mail mail = new Mail();
        mail.setMailId((String) map.get("mailId"));
        mail.setCountry((String) map.get("country"));
        mail.setWeight((Double) map.get("weight"));
        return mail;
    }
}
