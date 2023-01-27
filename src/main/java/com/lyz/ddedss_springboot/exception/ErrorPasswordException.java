package com.lyz.ddedss_springboot.exception;

public class ErrorPasswordException extends BaseException{

    public ErrorPasswordException() {
        super();
    }

    public ErrorPasswordException(String message) {
        super(message);
    }

    public ErrorPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorPasswordException(Throwable cause) {
        super(cause);
    }

    protected ErrorPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
