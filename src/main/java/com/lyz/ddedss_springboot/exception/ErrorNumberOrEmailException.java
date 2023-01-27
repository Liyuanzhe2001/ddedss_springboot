package com.lyz.ddedss_springboot.exception;

public class ErrorNumberOrEmailException extends BaseException{

    public ErrorNumberOrEmailException() {
        super();
    }

    public ErrorNumberOrEmailException(String message) {
        super(message);
    }

    public ErrorNumberOrEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorNumberOrEmailException(Throwable cause) {
        super(cause);
    }

    protected ErrorNumberOrEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
