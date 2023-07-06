package com.lyz.ddedss_springboot.exception;

public class FailedModifyNameException extends BaseException {
    public FailedModifyNameException() {
    }

    public FailedModifyNameException(String message) {
        super(message);
    }

    public FailedModifyNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedModifyNameException(Throwable cause) {
        super(cause);
    }

    public FailedModifyNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
