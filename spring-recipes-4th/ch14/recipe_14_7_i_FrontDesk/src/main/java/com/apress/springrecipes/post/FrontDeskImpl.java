package com.apress.springrecipes.post;

import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FrontDeskImpl implements FrontDesk {

    private final KafkaOperations<Integer, String> kafkaOperations;

    public FrontDeskImpl(KafkaOperations<Integer, String> kafkaOperations) {
        this.kafkaOperations = kafkaOperations;
    }

    public void sendMail(final Mail mail) {

        ListenableFuture<SendResult<Integer, String>> future = kafkaOperations.send("mails", convertToJson(mail));
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                System.out.println("Result (success): " + result.getRecordMetadata());
            }
        });
    }

    private String convertToJson(Mail mail) {
        try {
            return new ObjectMapper().writeValueAsString(mail);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
