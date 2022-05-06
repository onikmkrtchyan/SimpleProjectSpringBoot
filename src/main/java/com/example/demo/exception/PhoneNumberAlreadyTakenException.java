package com.example.demo.exception;

public class PhoneNumberAlreadyTakenException extends AlreadyTakenException {
    public PhoneNumberAlreadyTakenException(String phoneNumber) {
        super("The given phone number exist record: " + phoneNumber);
    }
}
