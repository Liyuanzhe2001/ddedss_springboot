package com.lyz.ddedss_springboot.exception;

public class InvitationCodeNotFoundException extends BaseException{

    public InvitationCodeNotFoundException() {
        super();
    }

    public InvitationCodeNotFoundException(String message) {
        super(message);
    }

    public InvitationCodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvitationCodeNotFoundException(Throwable cause) {
        super(cause);
    }

    protected InvitationCodeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
