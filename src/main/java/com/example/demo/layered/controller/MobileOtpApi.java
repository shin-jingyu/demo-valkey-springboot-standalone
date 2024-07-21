package com.example.demo.layered.controller;

import com.example.demo.layered.controller.dto.MobileOtpDto.OptSaveRequest;
import com.example.demo.layered.controller.dto.MobileOtpDto.OptSaveResponse;
import com.example.demo.layered.controller.dto.MobileOtpDto.OptSelectResponse;
import com.example.demo.layered.entity.MobileOtp;
import com.example.demo.layered.mapper.MobileOtpDtoMapper;
import com.example.demo.layered.usecase.MobileOptDeleteUseCase;
import com.example.demo.layered.usecase.MobileOptSelectUseCase;
import com.example.demo.layered.usecase.MobileOtpCreateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mobile")
@RequiredArgsConstructor
public class MobileOtpApi {

    private final MobileOtpCreateUseCase createUseCase;
    private final MobileOptSelectUseCase selectUseCase;
    private final MobileOptDeleteUseCase deleteUseCase;

    private final MobileOtpDtoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OptSaveResponse createMobileOtp(@RequestBody OptSaveRequest mobileOtp) {
        MobileOtp otp = createUseCase.mobileOtpCreate(mobileOtp);
        return mapper.toSaveResponse(otp);
    }

    @GetMapping("/{tel}")
    public OptSelectResponse selectMobileOtp(@PathVariable("tel") String tel) {
        return selectUseCase.selectMobileOtp(tel);
    }

    @DeleteMapping("/{tel}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMobileOtp(@PathVariable("tel") String tel) {
         deleteUseCase.deleteMobileOpt(tel);
    }
}
