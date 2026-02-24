package com.ummugul.libraryapi.repository;

import com.ummugul.libraryapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Author> findByFirstnameIgnoreCaseAndLastnameIgnoreCase(String firstname, String lastname);
}
