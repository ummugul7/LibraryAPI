package com.ummugul.libraryapi.Repository;

import com.ummugul.libraryapi.Model.Author;
import com.ummugul.libraryapi.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Author> findByFirstnameIgnoreCaseAndLastnameIgnoreCase(String firstname, String lastname);
}
