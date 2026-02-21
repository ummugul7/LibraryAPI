package com.ummugul.libraryapi.Service;

import com.ummugul.libraryapi.Model.Book;
import com.ummugul.libraryapi.Repository.IBookRepository;
import jakarta.persistence.Access;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final IBookRepository IBookRepository;

    public String AddBook( Book book){
        // kitap kaydı var mı diye bakıyoruz
        Optional<Book> existingBook = IBookRepository.findByNameIgnoreCase(book.getName());
        if(!existingBook.isPresent()){ // mevcut değilse
            IBookRepository.save(book);
            return "registration done";
        }
        else return "book already existing " ;
    }

    public Book GetBook(String name) {
        Book existingBook = IBookRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("book not found: " + name));
        return existingBook ;

    }


    public String DeleteBook(String name) {
        Optional<Book> existingBook = IBookRepository.findByNameIgnoreCase(name);
        if(existingBook.isPresent()){
            IBookRepository.deleteById(existingBook.get().getId());
            return "book deleted";
        }
        else return "book not found " ;

    }

    public Book UpdateBook(String name, @Valid Book book) {
        Book existingBook = IBookRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("book not found: " + name));

        BeanUtils.copyProperties(book, existingBook, "id"); // id hariç kopyala
        IBookRepository.save(existingBook);
          return book;
    }

}
