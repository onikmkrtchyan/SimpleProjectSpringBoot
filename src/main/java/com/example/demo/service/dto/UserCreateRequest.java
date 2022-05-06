package com.example.demo.service.dto;

import com.example.demo.utils.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Getter
@Setter
public class UserCreateRequest extends BaseUserDTO{
    @NotNull(message = "null")
    @NotEmpty(message = "Empty")
    @NotBlank(message = "blank")
    @ValidPassword(message = "Please correct your password: Password must contain at least 1 symbol," +
            " 1 special character, 1 lowercase and uppercase and contain 8 - 20 symbols")
    private String password;
}
