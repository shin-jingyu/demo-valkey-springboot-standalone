package com.example.demo.layered.usecase;


import com.example.demo.layered.controller.dto.MobileOtpDto.OptSelectResponse;

public interface MobileOptSelectUseCase {
    OptSelectResponse selectMobileOtp(String tel);
}
