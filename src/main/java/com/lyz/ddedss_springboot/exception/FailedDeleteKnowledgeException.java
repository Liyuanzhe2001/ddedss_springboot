package com.lyz.ddedss_springboot.exception;

public class FailedDeleteKnowledgeException extends BaseException{

    public FailedDeleteKnowledgeException() {
    }

    public FailedDeleteKnowledgeException(String message) {
        super(message);
    }

    public FailedDeleteKnowledgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedDeleteKnowledgeException(Throwable cause) {
        super(cause);
    }

    public FailedDeleteKnowledgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
