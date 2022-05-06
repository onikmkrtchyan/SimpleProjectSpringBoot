package com.example.demo.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BasePassportDTO {
    @NotBlank(message = "UID is blank")
    @Size(min = 1, max = 20, message = "should contain 1 - 20 symbols")
    private String UID;
}
