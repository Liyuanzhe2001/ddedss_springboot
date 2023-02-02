package com.lyz.ddedss_springboot.exception;

public class FailedCreateLessonException extends BaseException{
    public FailedCreateLessonException() {
        super();
    }

    public FailedCreateLessonException(String message) {
        super(message);
    }

    public FailedCreateLessonException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedCreateLessonException(Throwable cause) {
        super(cause);
    }

    protected FailedCreateLessonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
