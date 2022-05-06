package com.example.demo.controller;

import com.example.demo.service.PassportService;
import com.example.demo.service.dto.PassportCreateRequest;
import com.example.demo.service.dto.PassportResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/passport")
public class PassportController {
    PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody PassportCreateRequest passportCreateRequest){
        Long id = passportService.create(passportCreateRequest);
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<Page<PassportResponse>> getAll() {
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<PassportResponse> passportResponses = passportService.getAll(pageRequest);
        return ResponseEntity.ok(passportResponses);
    }
}
