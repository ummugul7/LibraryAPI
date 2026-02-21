package com.ummugul.libraryapi.Controller;

import com.ummugul.libraryapi.DTO.BookRequestDTO;
import com.ummugul.libraryapi.Model.Book;
import com.ummugul.libraryapi.Service.BookService;
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
    public  Book GetBook (@RequestParam String name){return bookService.GetBook(name);}

    @DeleteMapping("/delete")
    public  String DeleteBook (@RequestParam String name) {return bookService.DeleteBook(name);}

    @PutMapping("/update")
    public  Book UpdateBook (@RequestParam String name ,@Valid @RequestBody Book book ){
        return  bookService.UpdateBook(name , book );
    }

    @GetMapping("/list")
    public List<Book> ListBook(){
        return bookService.ListBook();
    }

    @GetMapping("/best")
    public List<Book> BestBook (){
        return bookService.BestBook();
    }
}
