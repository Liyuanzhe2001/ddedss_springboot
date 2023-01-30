package com.lyz.ddedss_springboot.exception;

public class ErrorNumberOrPasswordException extends BaseException{

    public ErrorNumberOrPasswordException() {
        super();
    }

    public ErrorNumberOrPasswordException(String message) {
        super(message);
    }

    public ErrorNumberOrPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorNumberOrPasswordException(Throwable cause) {
        super(cause);
    }

    protected ErrorNumberOrPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
