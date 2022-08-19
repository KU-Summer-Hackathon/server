package com.server.common.exception;

public class InternalServerException extends BoilerplateException {

    public InternalServerException(String message) {
        super(message, ErrorCode.INTERNAL_SERVER_EXCEPTION);
    }

    public InternalServerException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
