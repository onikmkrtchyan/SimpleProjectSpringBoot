package com.example.demo.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseWithCarList extends BaseUserDTO{
    List<BaseCarDTO> carResponses;
}
