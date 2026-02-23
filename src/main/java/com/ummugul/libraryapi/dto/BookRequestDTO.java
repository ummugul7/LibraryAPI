package com.ummugul.libraryapi.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {

    @NotBlank(message = "firstname  cannot be empty")
    private String firstname;

    @NotBlank(message = "lastname  cannot be empty")
    private String lastname;

    @NotBlank(message = "Book name cannot be empty")
    private String name;

    @Positive(message = "Page number must be positive.")
    private int page;

    private int score;
    @Positive(message = "price number must be positive.")
    private int price;
}