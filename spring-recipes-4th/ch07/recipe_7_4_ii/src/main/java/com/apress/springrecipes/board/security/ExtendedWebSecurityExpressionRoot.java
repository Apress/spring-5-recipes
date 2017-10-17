package com.apress.springrecipes.board.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

public class ExtendedWebSecurityExpressionRoot extends WebSecurityExpressionRoot {

    public ExtendedWebSecurityExpressionRoot(Authentication a, FilterInvocation fi) {
        super(a, fi);
    }

    public boolean localAccess() {
        return hasIpAddress("127.0.0.1") || hasIpAddress("0:0:0:0:0:0:0:1");

    }
}
