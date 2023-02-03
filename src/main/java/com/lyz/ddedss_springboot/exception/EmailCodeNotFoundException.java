package com.lyz.ddedss_springboot.exception;

public class EmailCodeNotFoundException extends BaseException{

    public EmailCodeNotFoundException() {
        super();
    }

    public EmailCodeNotFoundException(String message) {
        super(message);
    }

    public EmailCodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailCodeNotFoundException(Throwable cause) {
        super(cause);
    }

    protected EmailCodeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
