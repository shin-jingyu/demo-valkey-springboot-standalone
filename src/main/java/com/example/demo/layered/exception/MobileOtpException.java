package com.example.demo.layered.exception;

import com.example.demo.common.exception.support.CustomException;
import com.example.demo.common.exception.support.ErrorCode;

public class MobileOtpException extends CustomException {
    public MobileOtpException() {
        super();
    }

    public MobileOtpException(String message) {
        super(message);
    }

    public MobileOtpException(String message, Throwable cause) {
        super(message, cause);
    }

    public MobileOtpException(ErrorCode errorCode) {
        super(errorCode);
    }

    public MobileOtpException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
