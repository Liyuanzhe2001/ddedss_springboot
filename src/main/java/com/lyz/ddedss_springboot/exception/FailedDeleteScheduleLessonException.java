package com.lyz.ddedss_springboot.exception;

public class FailedDeleteScheduleLessonException extends BaseException{
    public FailedDeleteScheduleLessonException() {
    }

    public FailedDeleteScheduleLessonException(String message) {
        super(message);
    }

    public FailedDeleteScheduleLessonException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedDeleteScheduleLessonException(Throwable cause) {
        super(cause);
    }

    public FailedDeleteScheduleLessonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
