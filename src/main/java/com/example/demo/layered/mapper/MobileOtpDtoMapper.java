package com.example.demo.layered.mapper;

import com.example.demo.layered.controller.dto.MobileOtpDto.OptSaveRequest;
import com.example.demo.layered.controller.dto.MobileOtpDto.OptSaveResponse;
import com.example.demo.layered.controller.dto.MobileOtpDto.OptSelectResponse;
import com.example.demo.layered.entity.MobileOtp;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MobileOtpDtoMapper {
    MobileOtp toEntity(OptSaveRequest mobileOtpDto);
    OptSelectResponse toDto(MobileOtp entity);
    OptSaveResponse toSaveResponse(MobileOtp entity);
}
