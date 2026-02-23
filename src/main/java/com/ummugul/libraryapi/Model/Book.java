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

    private String name;

    private int page;

    private int score;

    private  int price;

    @ManyToOne // birden fazla kitabın 1 yazarı olabilir
    @JoinColumn(name = "author_id")
    private Author author; // book tablomda yazarın idsini tutuyorum
}
