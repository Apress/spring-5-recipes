package com.apress.springrecipes.board.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class AccessChecker {

    public boolean hasLocalAccess(Authentication authentication) {
        boolean access = false;
        if (authentication.getDetails() instanceof WebAuthenticationDetails) {
            WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
            String address = details.getRemoteAddress();
            access = address.equals("127.0.0.1") || address.equals("0:0:0:0:0:0:0:1");
        }
        return access;
    }
}
