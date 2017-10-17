package com.apress.springrecipes.social;

import org.springframework.social.UserIdSource;

public class StaticUserIdSource implements UserIdSource {
    @Override
    public String getUserId() {
        return "anonymous";
    }
}
