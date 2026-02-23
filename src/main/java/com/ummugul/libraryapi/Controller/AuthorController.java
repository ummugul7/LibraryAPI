package com.ummugul.libraryapi.Controller;

import com.ummugul.libraryapi.Model.Author;
import com.ummugul.libraryapi.Service.AuthorService;
import com.ummugul.libraryapi.dto.AuthorDetailDto;
import com.ummugul.libraryapi.dto.AuthorDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService ;


    @PostMapping("/save")
    public String SaveAuthor(@Valid @RequestBody Author author){ // bunu requestbody olarak almaya gerek yok aslında çünkü sadece firstname lastname gelecek yok lazımm galiba
        return authorService.SaveAuthor(author);
    }

    @GetMapping("/list")
    public List<AuthorDto> AuthorList(){
        return authorService.AuthorList();
    }

    @GetMapping("/detail")
    public AuthorDetailDto AuthorDetail(@RequestParam String firstname, @RequestParam String lastname){
        return  authorService.getAuthorWithBooks(firstname,lastname);
    }

    @DeleteMapping("/delete")
    public String DeleteAuthor(@RequestBody AuthorDto dto){
        return authorService.DeleteAuthor(dto);
    }
}
