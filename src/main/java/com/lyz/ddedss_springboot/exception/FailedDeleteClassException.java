package com.lyz.ddedss_springboot.exception;

public class FailedDeleteClassException extends BaseException{
    public FailedDeleteClassException() {
    }

    public FailedDeleteClassException(String message) {
        super(message);
    }

    public FailedDeleteClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedDeleteClassException(Throwable cause) {
        super(cause);
    }

    public FailedDeleteClassException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
