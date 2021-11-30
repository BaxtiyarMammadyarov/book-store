package az.mycompany.bookstore.client;


import az.mycompany.bookstore.model.Book;
import az.mycompany.bookstore.model.NewBook;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "book",url = "https://api.itbook.store/1.0")
public interface BookClient {


   @GetMapping("/new")
   NewBook getAll();
    @GetMapping("/books/{isbn13}")
    Book getBookByIsbn13(@PathVariable("isbn13") String isbn13);
}

