package com.ummugul.libraryapi.Controller;

import com.ummugul.libraryapi.Model.Book;
import com.ummugul.libraryapi.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/addbook")
    public String AddBook(@Valid @RequestBody Book book){return bookService.AddBook(book);}

    // http://localhost:8080/book/search?name=Budala
    @GetMapping ("/search")
    public  Book GetBook (@RequestParam String name){return bookService.GetBook(name);}

    @DeleteMapping("/delete")
    public  String DeleteBook (@RequestParam String name) {return bookService.DeleteBook(name);}

    @PutMapping("/update")
    public  Book UpdateBook (@RequestParam String name ,@Valid @RequestBody Book book ){
        return  bookService.UpdateBook(name , book );
    }
}
