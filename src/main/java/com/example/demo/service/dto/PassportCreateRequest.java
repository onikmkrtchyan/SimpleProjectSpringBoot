package com.example.demo.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class PassportCreateRequest extends BasePassportDTO {
    @NotNull
    private Long userId;
}