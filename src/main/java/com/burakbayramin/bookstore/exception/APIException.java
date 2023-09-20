package com.burakbayramin.bookstore.exception;

import org.springframework.http.HttpStatus;

public class APIException extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;

    public APIException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public APIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
