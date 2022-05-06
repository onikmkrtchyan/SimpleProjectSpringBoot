package com.example.demo.service.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BaseUserDTO {
    @NotNull(message = "null")
    @NotBlank(message = "blank")
    @Size(min = 1, max = 20, message = "should contain 1 - 20 symbols")
    private String name;
    @NotBlank(message = "blank")
    @NotEmpty(message = "Empty")
    @Size(min = 2, max = 20, message = "should contain 2 - 20 symbols")
    private String lastName;
    @NotNull(message = "null")
    @NotEmpty(message = "Empty")
    @NotBlank(message = "blank")
    @Size(min = 1, max = 20, message = "should contain 1 - 20 symbols")
    private String phoneNumber;
}