package com.example.demo.config;

import com.example.demo.data.jpa.entity.CarEntity;
import com.example.demo.data.jpa.entity.PassportEntity;
import com.example.demo.data.jpa.entity.UserEntity;
import com.example.demo.service.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapper {
    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);
    UserResponse toDTO(UserEntity userEntity);
    PassportResponse toResponse(PassportEntity passportEntity);
    CarResponse toResponse(CarEntity carEntity);

    UserResponseWithCarList toUserDTOWithCars(UserEntity userEntity);

    BaseCarDTO toBaseCarDTO(CarEntity carEntity);
}