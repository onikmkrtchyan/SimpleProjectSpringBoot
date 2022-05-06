package com.example.demo.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Setter
@Getter
public class BasePDFDTO {

    @Size(max = 20, message = "should contain 1 - 20 symbols")
    private String name;


}
