package az.mycompany.bookstore.client;


import az.mycompany.bookstore.model.Book;
import az.mycompany.bookstore.model.SearchBook;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@FeignClient(name = "book", url = "https://api.itbook.store/1.0")
public interface BookClient {


    @GetMapping("/new")
    SearchBook getAll();

    @GetMapping("/books/{isbn13}")
   Optional <Book> getBookByIsbn13(@PathVariable("isbn13") String isbn13);

    @GetMapping("/search/{search}")
    SearchBook getBookSearch(@PathVariable("search") String search);

}

