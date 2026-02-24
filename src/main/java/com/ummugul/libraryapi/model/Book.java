package com.ummugul.libraryapi.model;

import jakarta.persistence.*;
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
