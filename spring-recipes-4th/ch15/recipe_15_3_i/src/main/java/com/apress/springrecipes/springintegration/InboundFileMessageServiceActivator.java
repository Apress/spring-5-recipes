package com.apress.springrecipes.springintegration;

import java.io.File;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;


public class InboundFileMessageServiceActivator {
    private final Logger logger = LoggerFactory.getLogger(InboundFileMessageServiceActivator.class);

    @ServiceActivator
    public void interrogateMessage(Message<File> message) {
        MessageHeaders headers = message.getHeaders();
        for (Map.Entry<String, Object> header : headers.entrySet()) {
            logger.debug("{} : {}", header.getKey(), header.getValue() );
        }
    }
}
