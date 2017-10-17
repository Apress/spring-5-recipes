package com.apress.springrecipes.post;

import java.util.Locale;
import java.util.Random;

import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class FrontDeskImpl implements FrontDesk {

    private final KafkaOperations<Integer, Object> kafkaOperations;

    public FrontDeskImpl(KafkaOperations<Integer, Object> kafkaOperations) {
        this.kafkaOperations = kafkaOperations;
    }

    public void sendMail(final Mail mail) {

        ListenableFuture<SendResult<Integer, Object>> future = kafkaOperations.send("mails", mail);
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, Object>>() {

            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<Integer, Object> result) {
                System.out.println("Result (success): " + result.getRecordMetadata());
            }
        });
    }

    @Scheduled(fixedDelay = 250, initialDelay = 125)
    public void sendMailTrigger() {

        int countries = Locale.getISOCountries().length;
        Random rnd = new Random();
        String country = Locale.getISOCountries()[rnd.nextInt(countries)];

        sendMail(new Mail(String.valueOf(System.currentTimeMillis()), country, 1.5));
    }
}
