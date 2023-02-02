package com.lyz.ddedss_springboot.exception;

public class FailedCreateTeacherException extends BaseException{

    public FailedCreateTeacherException() {
    }

    public FailedCreateTeacherException(String message) {
        super(message);
    }

    public FailedCreateTeacherException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedCreateTeacherException(Throwable cause) {
        super(cause);
    }

    public FailedCreateTeacherException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
