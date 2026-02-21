package com.ummugul.libraryapi.Repository;

import com.ummugul.libraryapi.Model.Book;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository< Book, Integer> {


    Optional<Book> findByNameIgnoreCase(String name);

}
