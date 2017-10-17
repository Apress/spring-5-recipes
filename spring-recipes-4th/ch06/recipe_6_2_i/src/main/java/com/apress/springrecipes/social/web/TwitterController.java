package com.apress.springrecipes.social.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
@RequestMapping("/user/twitter")
public class TwitterController {

    private final ConnectionRepository repository;

    @Autowired
    public TwitterController(ConnectionRepository repository) {
        this.repository=repository;
    }

    @RequestMapping(value="/profile", method= RequestMethod.GET)
    public String showTwitterprofile(Model model) {
        Connection<Twitter> connection = repository.getPrimaryConnection(Twitter.class);
        model.addAttribute("profile", connection.fetchUserProfile());
        return "twitter-info";

    }
}
