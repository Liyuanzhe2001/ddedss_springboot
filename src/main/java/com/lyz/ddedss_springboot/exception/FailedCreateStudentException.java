package com.lyz.ddedss_springboot.exception;

public class FailedCreateStudentException extends BaseException{

    public FailedCreateStudentException() {
        super();
    }

    public FailedCreateStudentException(String message) {
        super(message);
    }

    public FailedCreateStudentException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedCreateStudentException(Throwable cause) {
        super(cause);
    }

    protected FailedCreateStudentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
