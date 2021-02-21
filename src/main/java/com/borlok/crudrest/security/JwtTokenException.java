package com.borlok.crudrest.security;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenException extends AuthenticationException {
    private int httpStatus;
    public JwtTokenException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtTokenException(String msg, int httpCode) {
        super(msg);
        this.httpStatus = httpCode;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
