package com.apress.springrecipes.post;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import org.springframework.jms.core.support.JmsGatewaySupport;
import org.springframework.jms.support.JmsUtils;

public class BackOfficeImpl extends JmsGatewaySupport implements BackOffice {

    public Mail receiveMail() {
        MapMessage message = (MapMessage) getJmsTemplate().receive();
        try {
            if (message == null) {
                return null;
            }
            Mail mail = new Mail();
            mail.setMailId(message.getString("mailId"));
            mail.setCountry(message.getString("country"));
            mail.setWeight(message.getDouble("weight"));
            return mail;
        } catch (JMSException e) {
            throw JmsUtils.convertJmsAccessException(e);
        }
    }
}
