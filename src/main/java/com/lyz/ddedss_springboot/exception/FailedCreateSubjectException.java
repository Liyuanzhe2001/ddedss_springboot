package com.lyz.ddedss_springboot.exception;

public class FailedCreateSubjectException extends BaseException{

    public FailedCreateSubjectException() {
    }

    public FailedCreateSubjectException(String message) {
        super(message);
    }

    public FailedCreateSubjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedCreateSubjectException(Throwable cause) {
        super(cause);
    }

    public FailedCreateSubjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
