package com.acro.dev.propmgnt.exception;

public class PropertyManagementException extends RuntimeException {
    public PropertyManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyManagementException(String message) {
        super(message);
    }
}