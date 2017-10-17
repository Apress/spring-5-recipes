package com.apress.springrecipes.vehicle;

import org.springframework.dao.DataIntegrityViolationException;

@SuppressWarnings("serial")
public class MyDuplicateKeyException extends DataIntegrityViolationException {
    public MyDuplicateKeyException(String msg) {
        super(msg);
    }

    public MyDuplicateKeyException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
