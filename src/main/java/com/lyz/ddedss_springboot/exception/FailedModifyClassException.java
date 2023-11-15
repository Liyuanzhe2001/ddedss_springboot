package com.lyz.ddedss_springboot.exception;

public class FailedModifyClassException extends BaseException {
    public FailedModifyClassException() {
    }

    public FailedModifyClassException(String message) {
        super(message);
    }

    public FailedModifyClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedModifyClassException(Throwable cause) {
        super(cause);
    }

    public FailedModifyClassException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
