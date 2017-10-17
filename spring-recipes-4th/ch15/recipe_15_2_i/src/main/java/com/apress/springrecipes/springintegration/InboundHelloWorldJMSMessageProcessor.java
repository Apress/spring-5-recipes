package com.apress.springrecipes.springintegration;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;


public class InboundHelloWorldJMSMessageProcessor {

    private final Logger logger = LoggerFactory.getLogger(InboundHelloWorldJMSMessageProcessor.class);

    @ServiceActivator
    public void handleIncomingJmsMessage(Message<Map<String, Object>> inboundJmsMessage)
            throws Throwable {
        Map<String, Object> msg = inboundJmsMessage.getPayload();
        logger.info("firstName: {}, lastName: {}, id: {}", msg.get("firstName"),
                                                            msg.get("lastName"),
                                                            msg.get("id"));

        // you can imagine what we could do here: put
        // the record into the database, call a websrvice,
        // write it to a file, etc, etc

    }
}
