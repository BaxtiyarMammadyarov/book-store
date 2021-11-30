package az.mycompany.bookstore.service.impl;

import az.mycompany.bookstore.client.BookClient;
import az.mycompany.bookstore.model.BookResponse;
import az.mycompany.bookstore.model.NewBook;
import az.mycompany.bookstore.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookServiceImpl implements BookService {

    final BookClient client;

    @Override
    public Page<NewBook> get(Pageable pageable) {
        return null;
    }

    @Override
    public List<BookResponse> getAllBooks() {
        NewBook book= client.getAll();
        List<BookResponse> books=new ArrayList<>();
        book.getBooks().stream().forEach(book1 -> books.add(book1));
        return books;
    }
}
