package com.apress.springrecipes.hello;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class BeansConfig {

    @Bean
    public Holiday christmas() {
        Holiday holiday = new Holiday();
        holiday.setGreeting("Merry Christmas!");
        holiday.setMonth(12);
        holiday.setDay(25);
        return holiday;
    }

    @Bean
    public Holiday newYear() {
        Holiday holiday = new Holiday();
        holiday.setGreeting("Happy New Year!");
        holiday.setMonth(12);
        holiday.setDay(31);
        return holiday;
    }

    @Bean
    public HelloWorld helloWorld(List<Holiday> holidays) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setMessage("How are you?");
        helloWorld.setHolidays(holidays);
        return helloWorld;
    }
}
