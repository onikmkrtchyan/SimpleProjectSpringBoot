package com.example.demo.exception;

public class IDAlreadyTakenException extends AlreadyTakenException{
    public IDAlreadyTakenException(Long id) {
        super("The given ID exist record: " + id);
    }
}
