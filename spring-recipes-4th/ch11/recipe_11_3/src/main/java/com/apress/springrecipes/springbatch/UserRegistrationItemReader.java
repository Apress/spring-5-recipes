package com.apress.springrecipes.springbatch;

import org.springframework.batch.item.ItemReader;

import java.util.Collection;
import java.util.Date;

public class UserRegistrationItemReader implements ItemReader<UserRegistration> {

    private final UserRegistrationService userRegistrationService;

    public UserRegistrationItemReader(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    public UserRegistration read() throws Exception {
        final Date today = new Date();
        Collection<UserRegistration> registrations = userRegistrationService.getOutstandingUserRegistrationBatchForDate(1, today);
        return registrations.stream().findFirst().orElse(null);
    }
}
