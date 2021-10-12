package com.users.management.infrastructure.exception;

public class InternalErrorException extends RuntimeException {

    public InternalErrorException(final String message) {
        super(message);
    }
}
