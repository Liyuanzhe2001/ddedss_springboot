package com.lyz.ddedss_springboot.exception;

public class FailedCreateUserException extends BaseException{

    public FailedCreateUserException() {
        super();
    }

    public FailedCreateUserException(String message) {
        super(message);
    }

    public FailedCreateUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedCreateUserException(Throwable cause) {
        super(cause);
    }

    protected FailedCreateUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
