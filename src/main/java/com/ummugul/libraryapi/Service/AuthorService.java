package com.ummugul.libraryapi.Service;

import com.ummugul.libraryapi.Model.Author;
import com.ummugul.libraryapi.Repository.IAuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorService {

    private final IAuthorRepository IAuthorRepository;

    public List<Author> GetAllAuthor() {
        List<Author> authorList=IAuthorRepository.findAll();
        if (authorList.isEmpty()) throw new RuntimeException("Author not found");
        else return authorList;
    }


    public String SaveAuthor(Author author) {
        Optional<Author> repositoryAuthor = IAuthorRepository.findByFirstnameIgnoreCaseAndLastnameIgnoreCase(author.getFirstname(),author.getLastname());
        if(!repositoryAuthor.isPresent()){
            IAuthorRepository.save(author);
            return "saved";
        }
        IAuthorRepository.save(author);
        return "author already existing";
    }
}
