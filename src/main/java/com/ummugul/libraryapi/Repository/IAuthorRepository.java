package com.ummugul.libraryapi.Repository;

import com.ummugul.libraryapi.Model.Author;
import com.ummugul.libraryapi.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Author> findByFirstnameIgnoreCaseAndLastnameIgnoreCase(String firstname, String lastname);
}
