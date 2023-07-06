package com.lyz.ddedss_springboot.exception;

public class FailedModifyNumberException extends BaseException {
    public FailedModifyNumberException() {
    }

    public FailedModifyNumberException(String message) {
        super(message);
    }

    public FailedModifyNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedModifyNumberException(Throwable cause) {
        super(cause);
    }

    public FailedModifyNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
