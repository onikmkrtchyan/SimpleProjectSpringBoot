package com.example.demo.exception;

public class CarNumberAlreadyTakenException extends AlreadyTakenException{
    public CarNumberAlreadyTakenException(String carNumber) {
        super("The given Car Number already exists: " + carNumber);
    }
}
