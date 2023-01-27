package com.lyz.ddedss_springboot.exception;

public class ErrorVerificationCodeException extends BaseException{

    public ErrorVerificationCodeException() {
        super();
    }

    public ErrorVerificationCodeException(String message) {
        super(message);
    }

    public ErrorVerificationCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorVerificationCodeException(Throwable cause) {
        super(cause);
    }

    protected ErrorVerificationCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
