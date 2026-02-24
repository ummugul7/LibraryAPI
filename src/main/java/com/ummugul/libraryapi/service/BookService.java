package com.ummugul.libraryapi.service;

import com.ummugul.libraryapi.dto.AuthorDto;
import com.ummugul.libraryapi.dto.BookRequestDTO;
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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final IBookRepository IBookRepository;
    private final IAuthorRepository IAuthorRepository;


    public String AddBook(BookRequestDTO dto) {
        Author author = IAuthorRepository
                .findByFirstnameIgnoreCaseAndLastnameIgnoreCase(dto.getFirstname(), dto.getLastname())
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setFirstname(dto.getFirstname());
                    newAuthor.setLastname(dto.getLastname());
                    return IAuthorRepository.save(newAuthor);
                });

        // kitap kaydı var mı diye bakıyoruz
        if (IBookRepository.findByNameIgnoreCase(dto.getName()).isPresent())
            return "Book already exists";

        Book book = new Book();
        BeanUtils.copyProperties(dto, book, "firstname", "lastname");
        book.setAuthor(author);
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

    // methodu kontol et
    public Book UpdateBook(String name, @Valid Book book) {
        Book existingBook = IBookRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new BookNotFoundException("book not found: " + name));

        BeanUtils.copyProperties(book, existingBook, "id"); // id hariç kopyala
        IBookRepository.save(existingBook);
        return book;
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
