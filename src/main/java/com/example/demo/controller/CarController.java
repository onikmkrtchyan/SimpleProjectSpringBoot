package com.example.demo.controller;

import com.example.demo.service.CarService;
import com.example.demo.service.dto.CarCreateRequest;
import com.example.demo.service.dto.CarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/car")
public class CarController {
   private final CarService carService;

    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody CarCreateRequest carCreateRequest){
        Long id = carService.create(carCreateRequest);
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<Page<CarResponse>> getAll() {
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<CarResponse> carResponses = carService.getAll(pageRequest);
        return ResponseEntity.ok(carResponses);
    }
}