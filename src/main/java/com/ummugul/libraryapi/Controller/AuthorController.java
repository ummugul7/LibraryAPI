package com.ummugul.libraryapi.Controller;

import com.ummugul.libraryapi.Model.Author;
import com.ummugul.libraryapi.Service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService ;


    @PostMapping("/save")
    public String SaveAuthor(@RequestBody Author author){ // bunu requestbody olarak almaya gerek yok aslında çünkü sadece firstname lastname gelecek yok lazımm galiba
        return authorService.SaveAuthor(author);
    }

    @GetMapping("/list")
    public List<Author> GetAllAuthor(){
        return authorService.GetAllAuthor();
    }
}
