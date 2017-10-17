package com.apress.springrecipes.reactive.court.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @GetMapping
    public String welcome(Model model) {
        model.addAttribute("today", Mono.just(LocalDate.now()));
        return "welcome";
    }

}
