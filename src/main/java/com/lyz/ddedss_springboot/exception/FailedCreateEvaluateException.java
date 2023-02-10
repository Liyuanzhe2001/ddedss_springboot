package com.lyz.ddedss_springboot.exception;

public class FailedCreateEvaluateException extends BaseException{

    public FailedCreateEvaluateException() {
    }

    public FailedCreateEvaluateException(String message) {
        super(message);
    }

    public FailedCreateEvaluateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedCreateEvaluateException(Throwable cause) {
        super(cause);
    }

    public FailedCreateEvaluateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
