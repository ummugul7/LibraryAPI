package com.ummugul.libraryapi.controller;

import com.ummugul.libraryapi.dto.BookRequestDTO;
import com.ummugul.libraryapi.dto.BookUpdateDto;
import com.ummugul.libraryapi.model.Book;
import com.ummugul.libraryapi.service.BookService;
import com.ummugul.libraryapi.dto.BookResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/addbook")
    public String AddBook(@Valid @RequestBody BookRequestDTO dto){return bookService.AddBook(dto);}

    // http://localhost:8080/book/search?name=Budala
    @GetMapping ("/search")
    public BookResponseDto GetBook (@RequestParam String name,@RequestParam String firstname,@RequestParam String lastname){return bookService.GetBook(name,firstname,lastname);}

    @DeleteMapping("/delete")
    public  String DeleteBook (@RequestParam String name,@RequestParam String firstname,@RequestParam String lastname) {return bookService.DeleteBook(name,firstname,lastname);}

    @PutMapping("/update")
    public  BookResponseDto UpdateBook (@RequestParam String bookname , @RequestParam String firstname,@RequestParam String lastname,@Valid @RequestBody BookUpdateDto book ){
        return  bookService.UpdateBook(bookname ,firstname,lastname , book );
    }

    @GetMapping("/list")
    public List<BookResponseDto> ListBook(){
        return bookService.ListBook();
    }

    @GetMapping("/best")
    public List<BookResponseDto> BestBook (){
        return bookService.BestBook();
    }
}
