// FINAL
package com.apress.springrecipes.court.web;

import com.apress.springrecipes.court.Delayer;
import com.apress.springrecipes.court.domain.Reservation;
import com.apress.springrecipes.court.service.ReservationService;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;


@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController {

    private final ReservationService reservationService;
    private final TaskExecutor taskExecutor;

    public ReservationQueryController(ReservationService reservationService, TaskExecutor taskExecutor) {
        this.reservationService = reservationService;
        this.taskExecutor = taskExecutor;
    }

    @GetMapping
    public void setupForm() {
    }

    // Controller will always look for a default POST method irrespective of name
    // when a submission ocurrs on the URL (i.e.@RequestMapping(/reservationQuery)) 
    // In this case, named submitForm to ease identification
    @PostMapping
    // Submission will come with courtName field, also add Model to return results 
    public Callable<String> sumbitForm(@RequestParam("courtName") String courtName, Model model) {

        return () -> {
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
        };
    }

    @GetMapping(params = "courtName")
    public ResponseBodyEmitter find(@RequestParam("courtName") String courtName) {
        final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        taskExecutor.execute(() -> {
            Collection<Reservation> reservations = reservationService.query(courtName);
            try {
                for (Reservation reservation : reservations) {
                    emitter.send(reservation);
                }
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }
}