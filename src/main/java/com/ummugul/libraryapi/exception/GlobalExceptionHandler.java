package com.ummugul.libraryapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity < Map> handleValidationException(
            MethodArgumentNotValidException ex){

        Map<String,String> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                        errorMap.put(error.getField(),
                                error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errorMap);
    }

    @ExceptionHandler({AuthorNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(AuthorNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({BookNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(BookNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

}

