package com.lyz.ddedss_springboot.exception;

public class FailedModifyPasswordException extends BaseException{

    public FailedModifyPasswordException() {
        super();
    }

    public FailedModifyPasswordException(String message) {
        super(message);
    }

    public FailedModifyPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedModifyPasswordException(Throwable cause) {
        super(cause);
    }

    protected FailedModifyPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
