package com.ummugul.libraryapi.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {
    private String firstname;
    private String lastname;
    private String name;
    private int page;
    private int score;
    private int price;
}