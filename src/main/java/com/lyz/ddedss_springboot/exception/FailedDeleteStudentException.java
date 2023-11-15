package com.lyz.ddedss_springboot.exception;

public class FailedDeleteStudentException extends BaseException {
    public FailedDeleteStudentException() {
    }

    public FailedDeleteStudentException(String message) {
        super(message);
    }

    public FailedDeleteStudentException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedDeleteStudentException(Throwable cause) {
        super(cause);
    }

    public FailedDeleteStudentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
