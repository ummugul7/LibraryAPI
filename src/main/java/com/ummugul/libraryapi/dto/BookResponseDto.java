package com.ummugul.libraryapi.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookResponseDto {
    private String name;
    private int page;
    private int score;
    private  int price;
    private AuthorDto author;

}
