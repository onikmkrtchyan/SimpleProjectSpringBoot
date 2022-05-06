package com.example.demo.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class ErrorResponse {
    private String  message;
    private HttpStatus status;
    private String request;
}
