package com.ummugul.libraryapi.Service;

import com.ummugul.libraryapi.Model.Author;
import com.ummugul.libraryapi.Model.Book;
import com.ummugul.libraryapi.Repository.IAuthorRepository;
import com.ummugul.libraryapi.Repository.IBookRepository;
import com.ummugul.libraryapi.dto.AuthorDetailDto;
import com.ummugul.libraryapi.dto.AuthorDto;
import com.ummugul.libraryapi.exception.AuthorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorService {

    private final IAuthorRepository IAuthorRepository;
    private  final IBookRepository IBookRepository;

    public List<AuthorDto> AuthorList(){

        List<Author> authorList=IAuthorRepository.findAll();
        if (authorList.isEmpty()) throw new AuthorNotFoundException("Author not found");

        return authorList.stream()
                .map(author -> {
                    AuthorDto dto = new AuthorDto();
                    BeanUtils.copyProperties(author, dto);
                    return dto;
                })
                .toList();
    }

    public String SaveAuthor(Author author) {
        Optional<Author> repositoryAuthor = IAuthorRepository.findByFirstnameIgnoreCaseAndLastnameIgnoreCase(author.getFirstname(),author.getLastname());
        if(!repositoryAuthor.isPresent()){
            IAuthorRepository.save(author);
            return "saved";
        }
        return "author already existing";
    }


    public AuthorDetailDto getAuthorWithBooks(String firstname, String lastname) {
        Author author = IAuthorRepository
                .findByFirstnameIgnoreCaseAndLastnameIgnoreCase(firstname, lastname)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found"));

        AuthorDetailDto dto = new AuthorDetailDto();
        dto.setFirstname(author.getFirstname());
        dto.setLastname(author.getLastname());
        dto.setBookNames(Optional.ofNullable(author.getBooks())
                .orElse(List.of())
                .stream()
                .map(Book::getName)
                .toList());

        return dto;
    }

    public String DeleteAuthor(AuthorDto dto) {
        Author author = IAuthorRepository
                .findByFirstnameIgnoreCaseAndLastnameIgnoreCase(dto.getFirstname(), dto.getLastname())
                .orElseThrow(() -> new AuthorNotFoundException("Author not found"));

        //int id = author.getId();
       // List<Book> bookList = IBookRepository.findAll();
       //   bookList.stream().filter(book -> id ==book.getAuthor().getId()).forEach(IBookRepository::delete);
        IAuthorRepository.delete(author);

        return  "deleted ";

    }
}
