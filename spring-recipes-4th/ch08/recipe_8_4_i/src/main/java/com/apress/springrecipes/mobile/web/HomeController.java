package com.apress.springrecipes.mobile.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by marten on 23-06-14.
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String index(HttpServletRequest request) {
        Device device = DeviceUtils.getCurrentDevice(request);
        if (device.isMobile()) {
            return "mobile/home";
        } else if (device.isTablet()) {
            return "tablet/home";
        } else {
            return "home";
        }
    }

}
