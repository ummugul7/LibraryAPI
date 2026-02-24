package com.ummugul.libraryapi.service;

import com.ummugul.libraryapi.dto.AuthorDto;
import com.ummugul.libraryapi.dto.BookRequestDTO;
import com.ummugul.libraryapi.dto.BookUpdateDto;
import com.ummugul.libraryapi.exception.AuthorNotFoundException;
import com.ummugul.libraryapi.model.Author;
import com.ummugul.libraryapi.model.Book;
import com.ummugul.libraryapi.repository.IAuthorRepository;
import com.ummugul.libraryapi.repository.IBookRepository;
import com.ummugul.libraryapi.dto.BookResponseDto;
import com.ummugul.libraryapi.exception.BookNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final IBookRepository IBookRepository;
    private final IAuthorRepository IAuthorRepository;


    public String AddBook(BookRequestDTO dto) {
        Optional<Author> existingAuthor = IAuthorRepository
                .findByFirstnameIgnoreCaseAndLastnameIgnoreCase(dto.getFirstname(), dto.getLastname());

        if (existingAuthor.isPresent()) {
            boolean bookExists = IBookRepository
                    .findByNameIgnoreCaseAndAuthor(dto.getName(), existingAuthor.get())
                    .isPresent();

            if (bookExists) {
                return "This book  already exists";
            }
        }

        Author author = existingAuthor.orElseGet(() -> {
            Author newAuthor = Author.builder()
                    .firstname(dto.getFirstname())
                    .lastname(dto.getLastname())
                    .build();
            return IAuthorRepository.save(newAuthor);
        });

        Book book = Book.builder()
                .name(dto.getName())
                .page(dto.getPage())
                .price(dto.getPrice())
                .score(dto.getScore())
                .author(author)
                .build();

        IBookRepository.save(book);
        return "Registration done";
    }


    public BookResponseDto GetBook(String name) {
        Book existingBook = IBookRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new BookNotFoundException("book not found: " + name));
        BookResponseDto dto = new BookResponseDto();
        BeanUtils.copyProperties(existingBook, dto, "author");
        AuthorDto authorDto = new AuthorDto();
        BeanUtils.copyProperties(existingBook.getAuthor(), authorDto);
        dto.setAuthor(authorDto);
        return dto;

    }


    public String DeleteBook(String name) {
        Optional<Book> existingBook = IBookRepository.findByNameIgnoreCase(name);
        if (existingBook.isPresent()) {
            IBookRepository.deleteById(existingBook.get().getId());
            return "book deleted";
        } else return "book not found ";

    }

    public BookResponseDto UpdateBook(String name, String firstname, String lastname , BookUpdateDto book) {

        Author existigAuthor = IAuthorRepository.findByFirstnameIgnoreCaseAndLastnameIgnoreCase(firstname,lastname)
                .orElseThrow(()->new AuthorNotFoundException("Author not found:" +firstname +" " + lastname));
        Book existingBook = IBookRepository.findByNameIgnoreCaseAndAuthor_Id(name,existigAuthor.getId())
                .orElseThrow(() -> new BookNotFoundException("book not found: " + name));

            existingBook.setPage(book.getPage());
            existingBook.setScore(book.getScore());
            existingBook.setPrice(book.getPrice());
            IBookRepository.save(existingBook);
            BookResponseDto dto = new BookResponseDto();
            BeanUtils.copyProperties(existingBook, dto, "author");
            AuthorDto authorDto = new AuthorDto();
            BeanUtils.copyProperties(existingBook.getAuthor(), authorDto);
            dto.setAuthor(authorDto);
            return dto;
    }

    public List<BookResponseDto> ListBook() {
        List<Book> bookList = IBookRepository.findAll();
        if (bookList.isEmpty())
            throw new BookNotFoundException("No books found");

        return bookList.stream()
                .map(book -> {
                    BookResponseDto dto = new BookResponseDto();
                    BeanUtils.copyProperties(book, dto, "author");

                    AuthorDto authorDto = new AuthorDto();
                    BeanUtils.copyProperties(book.getAuthor(), authorDto);
                    dto.setAuthor(authorDto);

                    return dto;
                })
                .toList();
    }


    public List<BookResponseDto> BestBook() {
        List<Book> allBooks = IBookRepository.findAllByOrderByScoreDesc();

        if (allBooks.isEmpty()) throw new BookNotFoundException("book not found");

        int topScore = allBooks.get(0).getScore();
        return allBooks.stream()
                .filter(book -> book.getScore() == topScore)
                .map(book -> {
                    BookResponseDto dto = new BookResponseDto();
                    BeanUtils.copyProperties(book, dto, "author");

                    AuthorDto authorDto = new AuthorDto();
                    BeanUtils.copyProperties(book.getAuthor(), authorDto);
                    dto.setAuthor(authorDto);

                    return dto;
                })
                .toList();


    }

}
