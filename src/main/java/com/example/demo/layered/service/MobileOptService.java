package com.example.demo.layered.service;

import com.example.demo.layered.controller.dto.MobileOtpDto.OptSaveRequest;
import com.example.demo.layered.controller.dto.MobileOtpDto.OptSelectResponse;
import com.example.demo.layered.entity.MobileOtp;
import com.example.demo.layered.exception.MobileOtpErrorCode;
import com.example.demo.layered.mapper.MobileOtpDtoMapper;
import com.example.demo.layered.repository.MobileOptRepository;
import com.example.demo.layered.usecase.MobileOptDeleteUseCase;
import com.example.demo.layered.usecase.MobileOptSelectUseCase;
import com.example.demo.layered.usecase.MobileOtpCreateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MobileOptService implements MobileOtpCreateUseCase, MobileOptSelectUseCase, MobileOptDeleteUseCase {

    private final MobileOptRepository mobileOptRepository;
    private final MobileOtpDtoMapper mobileOtpDtoMapper;


    @Override
    public MobileOtp mobileOtpCreate(MobileOtp mobileOtp) {
        Optional<MobileOtp> otp = mobileOptRepository.findByTel(mobileOtp.tel);
        // tel이 존재하면 삭제하고 새로 등록한다. (ifPresent = if + isPresent)
        otp.ifPresent(mobileOptRepository::delete);
        return mobileOptRepository.save(mobileOtp);
    }

    @Override
    public MobileOtp mobileOtpCreate(OptSaveRequest mobileOtpDto) {
        MobileOtp MobileOtp = mobileOtpDtoMapper.toEntity(mobileOtpDto);
        return mobileOtpCreate(MobileOtp);
    }

    @Override
    public OptSelectResponse selectMobileOtp(String tel) {
        // ttl 시간 줄어드는것 보여주기위해 tel로 id를 조회해서 두번 조회(한번에 tel로 조회하면 ttl 줄어드는것이 안보임)
        // 편하거보시오<>
        // ttl 줄어드는 걸 보여주려면 -> findById()까지 써서 두 번 조회
        // 아니면 -> findByTel만 써서 한 번만 조회(ttl은 안 줄어듦.)
        String key = mobileOptRepository.findIdByTel(tel)
                .orElseThrow(MobileOtpErrorCode.OTP_NOT_FOUND_BY_TEL::defaultException)
                .getId();
        MobileOtp otp1 = mobileOptRepository.findById(key)
                .orElseThrow(MobileOtpErrorCode.OTP_NOT_FOUND_BY_TEL::defaultException);
        return mobileOtpDtoMapper.toDto(otp1);
    }

    @Override
    public void deleteMobileOpt(String tel) {
        MobileOtp mobileOtp =mobileOptRepository.findByTel(tel)
                .orElseThrow(MobileOtpErrorCode.OTP_NOT_FOUND_BY_TEL::defaultException);
        mobileOptRepository.delete(mobileOtp);
    }
}
