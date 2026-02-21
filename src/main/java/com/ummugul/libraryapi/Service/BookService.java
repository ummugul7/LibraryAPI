package com.ummugul.libraryapi.Service;

import com.ummugul.libraryapi.DTO.BookRequestDTO;
import com.ummugul.libraryapi.Model.Author;
import com.ummugul.libraryapi.Model.Book;
import com.ummugul.libraryapi.Repository.IAuthorRepository;
import com.ummugul.libraryapi.Repository.IBookRepository;
import jakarta.persistence.Access;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final IBookRepository IBookRepository;
    private final IAuthorRepository IAuthorRepository;


    public String AddBook(BookRequestDTO dto){
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

    public List<Book> ListBook() {
      List<Book> bookList=IBookRepository.findAll();
      if (bookList.isEmpty())
          throw new RuntimeException("No books found");
        else return  bookList;
    }

    public List<Book> BestBook() {
        List<Book> allBooks = IBookRepository.findAllByOrderByScoreDesc();

        if (allBooks.isEmpty()) throw new RuntimeException("book not found");

        int topScore = allBooks.get(0).getScore();
        return allBooks.stream()
                .filter(book -> book.getScore() == topScore)
                .toList();


    }

}
