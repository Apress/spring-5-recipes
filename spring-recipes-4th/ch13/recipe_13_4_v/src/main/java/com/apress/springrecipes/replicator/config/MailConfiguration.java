package com.apress.springrecipes.replicator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.apress.springrecipes.replicator.EmailErrorNotifier;
import com.apress.springrecipes.replicator.ErrorNotifier;

/**
 * Created by marten on 26-05-14.
 */
@Configuration
public class MailConfiguration {

    @Bean
    public ErrorNotifier errorNotifier() {
        EmailErrorNotifier errorNotifier = new EmailErrorNotifier();
        errorNotifier.setMailSender(mailSender());
        errorNotifier.setCopyErrorMailMessage(copyErrorMailMessage());
        return errorNotifier;
    }

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("localhost");
        mailSender.setPort(25);
        mailSender.setUsername("system");
        mailSender.setPassword("12345");
        return mailSender;
    }

    @Bean
    public SimpleMailMessage copyErrorMailMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("system@localhost");
        message.setTo("admin@localhost");
        message.setSubject("File Copy Error");
        message.setText("Dear Administrator,\n" +
                "\n" +
                "                       An error occurred when copying the following file :\n" +
                "\t\t       Source directory : %s\n" +
                "\t\t       Destination directory : %s\n" +
                "\t\t       Filename : %s");
        return message;
    }
}
