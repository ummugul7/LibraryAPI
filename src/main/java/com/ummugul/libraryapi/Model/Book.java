package com.ummugul.libraryapi.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;

    @NotBlank(message = "Book name cannot be empty")
    private String name;

    @Positive(message = "Page number must be positive.")
    private int page;

    private int score;

    @Positive(message = "price number must be positive.")
    private  int price;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private Author author;
}
