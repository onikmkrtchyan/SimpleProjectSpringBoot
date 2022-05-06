package com.example.demo.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassportResponse extends BasePassportDTO {
    private Long id;
    private UserResponse user;
}