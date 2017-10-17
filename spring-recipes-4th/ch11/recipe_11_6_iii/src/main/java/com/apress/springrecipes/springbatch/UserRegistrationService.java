package com.apress.springrecipes.springbatch;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.Collection;
import java.util.Date;

public interface UserRegistrationService {
    Collection<UserRegistration> getOutstandingUserRegistrationBatchForDate(int quantity, Date date);

    @Retryable(backoff = @Backoff(delay = 1000, maxDelay = 10000, multiplier = 2))
    UserRegistration registerUser(UserRegistration userRegistrationRegistration);
}
