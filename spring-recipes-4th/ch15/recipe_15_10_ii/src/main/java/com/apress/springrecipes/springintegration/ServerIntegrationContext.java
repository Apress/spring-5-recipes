package com.apress.springrecipes.springintegration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.jms.connection.CachingConnectionFactory;

import com.apress.springrecipes.springintegration.myholiday.VacationServiceImpl;

@Configuration
@EnableIntegration
public class ServerIntegrationContext {

    @Bean
    public CachingConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        connectionFactory.setTrustAllPackages(true);
        return new CachingConnectionFactory(connectionFactory);
    }

    @Bean
    public VacationServiceImpl vacationService() {
        return new VacationServiceImpl();
    }

    @Bean
    public IntegrationFlow serverIntegrationFlow() {
        return IntegrationFlows.from(
                    Jms.inboundGateway(connectionFactory()).destination("inboundHotelReservationSearchDestination"))
                .handle(vacationService())
                .get();
    }
}
