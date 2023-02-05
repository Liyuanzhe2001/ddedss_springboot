package com.lyz.ddedss_springboot.exception;

// 权限不足
public class InsufficientPermissionException extends BaseException {

    public InsufficientPermissionException() {
    }

    public InsufficientPermissionException(String message) {
        super(message);
    }

    public InsufficientPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientPermissionException(Throwable cause) {
        super(cause);
    }

    public InsufficientPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
