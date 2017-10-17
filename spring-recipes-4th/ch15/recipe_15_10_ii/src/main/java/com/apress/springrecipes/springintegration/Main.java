package com.apress.springrecipes.springintegration;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.springintegration.myholiday.HotelReservation;
import com.apress.springrecipes.springintegration.myholiday.HotelReservationSearch;
import com.apress.springrecipes.springintegration.myholiday.VacationService;


public class Main {
    public static void main(String[] args) throws Throwable {
        // Start server
        ConfigurableApplicationContext serverCtx = new AnnotationConfigApplicationContext(ServerIntegrationContext.class);

        // Start client and issue search
        ConfigurableApplicationContext clientCtx = new AnnotationConfigApplicationContext(ClientIntegrationContext.class);

        VacationService vacationService = clientCtx.getBean(VacationService.class);

        LocalDate now = LocalDate.now();
        Date start = Date.from(now.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date stop = Date.from(now.plusDays(8).atStartOfDay(ZoneId.systemDefault()).toInstant());
        HotelReservationSearch hotelReservationSearch = new HotelReservationSearch(200f, 2, start, stop);
        List<HotelReservation> results = vacationService.findHotels(hotelReservationSearch);

        System.out.printf("Found %s results.%n", results.size());
        results.forEach(r -> System.out.printf("\t%s%n", r));


        serverCtx.close();
        clientCtx.close();
    }
}
