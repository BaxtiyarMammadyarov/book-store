package az.mycompany.bookstore.service;


import az.mycompany.bookstore.model.BookResponse;
import az.mycompany.bookstore.model.NewBook;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    Page<NewBook> get(Pageable pageable);

    List<BookResponse> getAllBooks();
}
