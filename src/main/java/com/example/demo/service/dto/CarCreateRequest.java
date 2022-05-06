package com.example.demo.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
public class CarCreateRequest extends BaseCarDTO{
    @NotNull
    private Long userId;
}
