package com.lyz.ddedss_springboot.exception;

public class FailedSendMessageException extends BaseException{

    public FailedSendMessageException() {
        super();
    }

    public FailedSendMessageException(String message) {
        super(message);
    }

    public FailedSendMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedSendMessageException(Throwable cause) {
        super(cause);
    }

    protected FailedSendMessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
