package com.lyz.ddedss_springboot.exception;

public class NumberOrPasswordException extends BaseException{

    public NumberOrPasswordException() {
        super();
    }

    public NumberOrPasswordException(String message) {
        super(message);
    }

    public NumberOrPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberOrPasswordException(Throwable cause) {
        super(cause);
    }

    protected NumberOrPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
