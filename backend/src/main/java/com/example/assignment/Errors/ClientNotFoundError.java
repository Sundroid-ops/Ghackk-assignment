package com.example.assignment.Errors;

public class ClientNotFoundError extends RuntimeException{
    public ClientNotFoundError() {
    }

    public ClientNotFoundError(String message) {
        super(message);
    }

    public ClientNotFoundError(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientNotFoundError(Throwable cause) {
        super(cause);
    }

    public ClientNotFoundError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
