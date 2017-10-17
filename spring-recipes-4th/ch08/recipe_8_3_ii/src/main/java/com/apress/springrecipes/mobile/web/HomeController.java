package com.apress.springrecipes.mobile.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by marten on 23-06-14.
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String index() {
        return "home";
    }

}
