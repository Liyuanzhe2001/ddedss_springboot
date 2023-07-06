package com.lyz.ddedss_springboot.exception;

public class FailedModifyEmailException extends BaseException {
    public FailedModifyEmailException() {
    }

    public FailedModifyEmailException(String message) {
        super(message);
    }

    public FailedModifyEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedModifyEmailException(Throwable cause) {
        super(cause);
    }

    public FailedModifyEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
