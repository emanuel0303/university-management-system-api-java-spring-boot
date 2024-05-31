package com.ums.Universitymanagementsystem.exception;

public class ExamNotFoundException extends RuntimeException {

    public ExamNotFoundException() {
        super();
    }

    public ExamNotFoundException(String message) {
        super(message);
    }

    public ExamNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
