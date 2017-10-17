// FINAL
package com.apress.springrecipes.court.web;

import com.apress.springrecipes.court.Delayer;
import com.apress.springrecipes.court.domain.Reservation;
import com.apress.springrecipes.court.service.ReservationService;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController {

    private final ReservationService reservationService;
    private final AsyncListenableTaskExecutor taskExecutor;

    public ReservationQueryController(ReservationService reservationService, AsyncListenableTaskExecutor taskExecutor) {
        this.reservationService = reservationService;
        this.taskExecutor = taskExecutor;
    }

    @GetMapping
    public void setupForm() {}

    // Controller will always look for a default POST method irrespective of name
    // when a submission ocurrs on the URL (i.e.@RequestMapping(/reservationQuery)) 
    // In this case, named submitForm to ease identification
    @PostMapping
    // Submission will come with courtName field, also add Model to return results 
    public ListenableFuture<String> sumbitForm(@RequestParam("courtName") String courtName, Model model) {

        return taskExecutor.submitListenable(() -> {
            // Create reservation list
            List<Reservation> reservations = java.util.Collections.emptyList();
            // Make a query if parameter is not null
            if (courtName != null) {
                Delayer.randomDelay(); // Simulate a slow service
                reservations = reservationService.query(courtName);
            }
            // Update model to include reservations
            model.addAttribute("reservations", reservations);
            // Return view as a string
            // Based on resolver configuration the reservationQuery view
            // will be mapped to a JSP in /WEB-INF/jsp/reservationQuery.jsp
            return "reservationQuery";
        });
    }
}