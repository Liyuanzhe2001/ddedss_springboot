package com.lyz.ddedss_springboot.exception;

public class FailedDeleteLessonException extends BaseException{
    public FailedDeleteLessonException() {
    }

    public FailedDeleteLessonException(String message) {
        super(message);
    }

    public FailedDeleteLessonException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedDeleteLessonException(Throwable cause) {
        super(cause);
    }

    public FailedDeleteLessonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
