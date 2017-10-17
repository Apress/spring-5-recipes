package com.apress.springrecipes.springintegration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.jms.connection.CachingConnectionFactory;

import com.apress.springrecipes.springintegration.myholiday.VacationService;

@Configuration
@EnableIntegration
public class ClientIntegrationContext {

    @Bean
    public CachingConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        connectionFactory.setTrustAllPackages(true);
        return new CachingConnectionFactory(connectionFactory);
    }

    @Bean
    public IntegrationFlow vacationGatewayFlow() {
        return IntegrationFlows
                .from(VacationService.class)
                .handle(
                        Jms.outboundGateway(connectionFactory())
                                .requestDestination("inboundHotelReservationSearchDestination")
                                .replyDestination("outboundHotelReservationSearchResultsDestination"))
                .get();
    }

}
