package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //get all users which name contains given String
    @GetMapping("/filter")
    public ResponseEntity<List<UserResponse>> getUsersContainsString(@RequestParam  String input) {
        List<UserResponse> userResponse = userService.getUsersContainsString(input);
        return ResponseEntity.ok(userResponse);
    }

    //get all cars by user_id
    @GetMapping("/{id}/cars")
    public ResponseEntity<UserResponseWithCarList> getCarsById(@PathVariable Long id) {
        UserResponseWithCarList userResponseWithCarList = userService.getWithCars(id);
        return ResponseEntity.ok(userResponseWithCarList);
    }

    //Create new user via JSON
    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody UserCreateRequest user){
        Long id = userService.create(user);
        return ResponseEntity.ok(id);
    }

    //Get All users
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserResponse> userResponse = userService.getAll();
        return ResponseEntity.ok(userResponse);
    }

    //Get By ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        UserResponse userResponse = userService.getByID(id);
        return ResponseEntity.ok(userResponse);
    }

    //Get by Phone Number
    @GetMapping("/getByPhoneNumber/{phoneNumber}")
    public ResponseEntity<UserResponse> getByPhoneNumber(@PathVariable String phoneNumber) {
        UserResponse userResponse = userService.getByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(userResponse);
    }
}