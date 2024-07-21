package com.example.demo.layered.usecase;

import com.example.demo.layered.controller.dto.MobileOtpDto.OptSaveRequest;
import com.example.demo.layered.entity.MobileOtp;

public interface MobileOtpCreateUseCase {
    MobileOtp mobileOtpCreate(MobileOtp mobileOtp);
    MobileOtp mobileOtpCreate(OptSaveRequest mobileOtpDto);
}
