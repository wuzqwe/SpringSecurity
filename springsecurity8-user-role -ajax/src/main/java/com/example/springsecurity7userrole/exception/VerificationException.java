package com.example.springsecurity7userrole.exception;


import org.springframework.security.core.AuthenticationException;

public class VerificationException extends AuthenticationException {

    public VerificationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public VerificationException(String msg) {
        super(msg);
    }

    public VerificationException() {
        super("验证错误，请重新输入");
    }
}
