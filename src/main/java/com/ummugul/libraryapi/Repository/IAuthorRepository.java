package com.ummugul.libraryapi.Repository;

import com.ummugul.libraryapi.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorRepository extends JpaRepository<Author, Integer> {

}
