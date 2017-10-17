package com.apress.springrecipes.social.web;

import org.springframework.social.connect.UserProfile;

/**
 * Simple Signup form contains only the username and password.
 *
 * @author Marten Deinum
 */
public class SignupForm {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static SignupForm fromProviderUser(UserProfile providerUser) {
        SignupForm form = new SignupForm();
        form.setUsername(providerUser.getUsername());
        return form;
    }
}
