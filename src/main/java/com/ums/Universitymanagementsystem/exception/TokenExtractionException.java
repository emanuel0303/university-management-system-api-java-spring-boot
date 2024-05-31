package com.ums.Universitymanagementsystem.exception;

public class TokenExtractionException extends RuntimeException {
    public TokenExtractionException(String message) {
        super(message);
    }

    public TokenExtractionException(String message, Throwable cause) {
        super(message, cause);
    }
}
