package com.lyz.ddedss_springboot.exception;

public class FailedCreateTeacherSubjectException extends BaseException{

    public FailedCreateTeacherSubjectException() {
    }

    public FailedCreateTeacherSubjectException(String message) {
        super(message);
    }

    public FailedCreateTeacherSubjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedCreateTeacherSubjectException(Throwable cause) {
        super(cause);
    }

    public FailedCreateTeacherSubjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
