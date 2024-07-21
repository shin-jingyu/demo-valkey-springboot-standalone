package com.example.demo.layered.controller.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public final class MobileOtpDto {

    private MobileOtpDto(){}

    @Builder
    public record OptSaveRequest(
            @NotBlank
            @Pattern(regexp ="/^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/")
            String tel,
            @NotBlank
            String otp,
            @NotBlank
            Integer ttl
    ){

    }

    @Builder
    public record OptSelectRequst(
            @NotBlank
            @Pattern(regexp ="/^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/")
            String tel,
            String otp
    ){

    }

    @Builder
    public record OptDeleteRequst(
            @NotBlank
            @Pattern(regexp ="/^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/")
            String tel,
            String otp
    ){

    }

    @Builder
    public record OptSaveResponse(
            String tel,
            String otp,
            Integer ttl
    ){

    }

    @Builder
    public record OptSelectResponse(
            @NotBlank
            String tel,
            String otp,
            Integer ttl
    ){
    }

    @Builder
    public record OptDeleteResponse(
            String tel
    ){}

}
