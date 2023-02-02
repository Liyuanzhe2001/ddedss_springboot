package com.lyz.ddedss_springboot.exception;

public class FailedDeleteUserException extends BaseException{

    public FailedDeleteUserException() {
    }

    public FailedDeleteUserException(String message) {
        super(message);
    }

    public FailedDeleteUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedDeleteUserException(Throwable cause) {
        super(cause);
    }

    public FailedDeleteUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
