package com.lyz.ddedss_springboot.exception;

public class FailedCreateKnowledgeException extends BaseException{

    public FailedCreateKnowledgeException() {
    }

    public FailedCreateKnowledgeException(String message) {
        super(message);
    }

    public FailedCreateKnowledgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedCreateKnowledgeException(Throwable cause) {
        super(cause);
    }

    public FailedCreateKnowledgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
