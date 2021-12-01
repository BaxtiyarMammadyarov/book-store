package az.mycompany.bookstore.service;


import az.mycompany.bookstore.model.BookResponse;



import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;


@Service
public interface BookService {

    Page<BookResponse> getAllBooks(int pageNumber, int size);

    Page<BookResponse> getSearch(String search, int pageNumber, int size);
}
