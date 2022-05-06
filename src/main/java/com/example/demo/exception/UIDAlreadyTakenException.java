package com.example.demo.exception;

public class UIDAlreadyTakenException extends AlreadyTakenException{
    public UIDAlreadyTakenException(String UID) {
        super("The given UID exist record: " + UID);
    }
}
