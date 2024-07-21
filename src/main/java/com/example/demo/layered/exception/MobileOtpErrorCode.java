package com.example.demo.layered.exception;

import com.example.demo.common.exception.support.ErrorCode;
import org.springframework.http.HttpStatus;

public enum MobileOtpErrorCode implements ErrorCode {
    OTP_NOT_FOUND_BY_TEL("해당 번호로 등록된 OTP없습니다.", HttpStatus.NOT_FOUND)
    ;


    private final String message;
    private final HttpStatus status;

    MobileOtpErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String defaultMessage() {
        return message;
    }

    @Override
    public HttpStatus defaultHttpStatus() {
        return status;
    }

    @Override
    public MobileOtpException defaultException() {
        return new MobileOtpException(this);
    }

    @Override
    public MobileOtpException defaultException(Throwable cause) {
        return new MobileOtpException(this, cause);
    }
}
