package com.example.demo.exception;

public class AlreadyTakenException extends RuntimeException{
    AlreadyTakenException(String string){
        super(string);
    }
}
