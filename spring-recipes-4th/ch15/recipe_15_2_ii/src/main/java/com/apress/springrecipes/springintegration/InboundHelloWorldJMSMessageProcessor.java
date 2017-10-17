package com.apress.springrecipes.springintegration;

import javax.jms.MapMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

public class InboundHelloWorldJMSMessageProcessor {

    private final Logger logger = LoggerFactory.getLogger(InboundHelloWorldJMSMessageProcessor.class);

    @ServiceActivator
    public void handleIncomingJmsMessageWithPayloadNotExtracted(
            Message<javax.jms.Message> msgWithJmsMessageAsPayload) throws Throwable {
        javax.jms.MapMessage jmsMessage = (MapMessage) msgWithJmsMessageAsPayload.getPayload();
        logger.debug("firstName: {}, lastName: {}, id: {}", jmsMessage.getString("firstName"),
                                                            jmsMessage.getString("lastName"),
                                                            jmsMessage.getLong("id"));

        // you can imagine what we could do here: put
        // the record into the database, call a websrvice,
        // write it to a file, etc, etc

    }

}
