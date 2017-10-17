package com.apress.springrecipes.mobile.web;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by marten on 23-06-14.
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String index(Device device) {
        if (device.isMobile()) {
            return "mobile/home";
        } else if (device.isTablet()) {
            return "tablet/home";
        } else {
            return "home";
        }
    }

}
