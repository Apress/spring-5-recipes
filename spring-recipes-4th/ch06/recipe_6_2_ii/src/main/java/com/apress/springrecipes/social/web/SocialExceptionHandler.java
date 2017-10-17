package com.apress.springrecipes.social.web;

import org.springframework.social.connect.NotConnectedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by marten on 08-09-14.
 */
@ControllerAdvice
public class SocialExceptionHandler  {

    @ExceptionHandler(NotConnectedException.class)
    public String handleNotConnected(NotConnectedException nce) {
        return "redirect:/connect/" + nce.getProviderId();
    }
}
