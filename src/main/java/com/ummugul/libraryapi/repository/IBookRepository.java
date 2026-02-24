package com.ummugul.libraryapi.repository;

import com.ummugul.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository< Book, Integer> {


    Optional<Book> findByNameIgnoreCase(String name);
    List<Book> findAllByOrderByScoreDesc();
}
