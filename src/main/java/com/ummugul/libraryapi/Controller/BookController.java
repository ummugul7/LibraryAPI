package com.ummugul.libraryapi.Controller;

import com.ummugul.libraryapi.dto.BookRequestDTO;
import com.ummugul.libraryapi.Model.Book;
import com.ummugul.libraryapi.Service.BookService;
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
    public BookResponseDto GetBook (@RequestParam String name){return bookService.GetBook(name);}

    @DeleteMapping("/delete")
    public  String DeleteBook (@RequestParam String name) {return bookService.DeleteBook(name);}

    @PutMapping("/update")
    public  Book UpdateBook (@RequestParam String name ,@Valid @RequestBody Book book ){
        return  bookService.UpdateBook(name , book );
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
