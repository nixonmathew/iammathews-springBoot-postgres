package com.self.portfolio.exception;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException {

    private int statusCode;

    public UserException(String msg) {
        super(msg);
        this.statusCode = HttpStatus.BAD_REQUEST.value();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
