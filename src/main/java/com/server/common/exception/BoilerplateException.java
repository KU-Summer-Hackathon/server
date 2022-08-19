package com.server.common.exception;

import lombok.Getter;

@Getter
public abstract class BoilerplateException extends RuntimeException {

    private final ErrorCode errorCode;

    public BoilerplateException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return errorCode.getStatus();
    }
}
