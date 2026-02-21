package com.ummugul.libraryapi.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;

    @NotBlank(message = "firstname  cannot be empty")
    private String firstname;

    @NotBlank(message = "lastname  cannot be empty")
    private String lastname;

    @OneToMany
    private List<Book> books;

}
