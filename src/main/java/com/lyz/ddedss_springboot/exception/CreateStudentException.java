package com.lyz.ddedss_springboot.exception;

public class CreateStudentException extends BaseException{

    public CreateStudentException() {
        super();
    }

    public CreateStudentException(String message) {
        super(message);
    }

    public CreateStudentException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateStudentException(Throwable cause) {
        super(cause);
    }

    protected CreateStudentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
