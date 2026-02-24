package com.ummugul.libraryapi.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String messagee){
        super(messagee);
    }
}
