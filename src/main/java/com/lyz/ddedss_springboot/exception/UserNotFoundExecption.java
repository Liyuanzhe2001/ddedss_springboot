package com.lyz.ddedss_springboot.exception;

public class UserNotFoundExecption extends BaseException{

    public UserNotFoundExecption() {
    }

    public UserNotFoundExecption(String message) {
        super(message);
    }

    public UserNotFoundExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundExecption(Throwable cause) {
        super(cause);
    }

    public UserNotFoundExecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
