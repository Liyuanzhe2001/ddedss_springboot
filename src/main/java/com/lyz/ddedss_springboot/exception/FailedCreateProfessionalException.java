package com.lyz.ddedss_springboot.exception;

public class FailedCreateProfessionalException extends BaseException{
    public FailedCreateProfessionalException() {
        super();
    }

    public FailedCreateProfessionalException(String message) {
        super(message);
    }

    public FailedCreateProfessionalException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedCreateProfessionalException(Throwable cause) {
        super(cause);
    }

    protected FailedCreateProfessionalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
