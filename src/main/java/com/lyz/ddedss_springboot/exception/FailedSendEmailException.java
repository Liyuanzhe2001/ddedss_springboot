package com.lyz.ddedss_springboot.exception;

public class FailedSendEmailException extends BaseException{

    public FailedSendEmailException() {
        super();
    }

    public FailedSendEmailException(String message) {
        super(message);
    }

    public FailedSendEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedSendEmailException(Throwable cause) {
        super(cause);
    }

    protected FailedSendEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
