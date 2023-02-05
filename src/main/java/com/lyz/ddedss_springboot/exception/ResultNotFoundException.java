package com.lyz.ddedss_springboot.exception;

public class ResultNotFoundException extends BaseException{

    public ResultNotFoundException() {
        super();
    }

    public ResultNotFoundException(String message) {
        super(message);
    }

    public ResultNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ResultNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
