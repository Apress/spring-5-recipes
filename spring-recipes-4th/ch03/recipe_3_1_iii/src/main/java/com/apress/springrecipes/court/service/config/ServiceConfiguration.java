package com.apress.springrecipes.court.service.config;

import com.apress.springrecipes.court.service.ReservationService;
import com.apress.springrecipes.court.service.ReservationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by marten on 28-04-14.
 */
@Configuration
public class ServiceConfiguration {

    @Bean
    public ReservationService reservationService() {
        return new ReservationServiceImpl();
    }
}
