package com.apress.springrecipes.social.web;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/twitter")
public class TwitterController {

    private final ConnectionRepository repository;

    public TwitterController(ConnectionRepository repository) {
        this.repository=repository;
    }

    @GetMapping(value="/profile")
    public String showTwitterprofile(Model model) {
        Connection<Twitter> connection = repository.getPrimaryConnection(Twitter.class);
        model.addAttribute("profile", connection.fetchUserProfile());
        return "twitter-info";

    }
}
