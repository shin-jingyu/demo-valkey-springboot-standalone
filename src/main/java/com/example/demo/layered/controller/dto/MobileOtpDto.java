package com.example.demo.layered.controller.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public final class MobileOtpDto {

    private MobileOtpDto(){}

    @Builder
    public record OptSaveRequest(
            @NotBlank
            @Pattern(regexp = "^01(0|1|[6-9])-?\\d{3,4}-?\\d{4}$")
            String tel,
            @NotBlank
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
            String tel,
            String otp,
            Integer ttl
    ){
    }
}
