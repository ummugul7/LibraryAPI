package com.ummugul.libraryapi.exception;

public class AuthorNotFoundException extends RuntimeException{

    public AuthorNotFoundException(String message){
            super(message);
    }
}
