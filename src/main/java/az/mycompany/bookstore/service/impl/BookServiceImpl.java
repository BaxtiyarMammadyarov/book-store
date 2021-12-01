package az.mycompany.bookstore.service.impl;

import az.mycompany.bookstore.client.BookClient;
import az.mycompany.bookstore.model.BookResponse;

import az.mycompany.bookstore.service.BookService;

import az.mycompany.bookstore.validation.PageValidation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;


import java.awt.image.RescaleOp;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Log4j2
public class BookServiceImpl implements BookService {

    final BookClient client;
    final PageValidation pageValidation;



    @Override
    public Page<BookResponse> getAllBooks(int pageNumber, int size) {
        List<BookResponse> books=client.getAll().getBooks();
        int pageSize = size;
        int currentPage =pageNumber;
        int startItem = currentPage * pageSize;

        List<BookResponse> list;
        if (books.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, books.size());
            list = books.subList(startItem, toIndex);
        }

        Page<BookResponse> bookPage
                = new PageImpl<BookResponse>(list, PageRequest.of(currentPage, pageSize), books.size());

        return bookPage;

    }

    @Override
    public Page<BookResponse> getSearch(String search, int pageNumber, int size) {
      List<BookResponse>responses=  client.getBookSearch(search).getBooks();
      pageValidation.getPage(responses,pageNumber,size);

        return  pageValidation.getPage(responses,pageNumber,size);
    }


}
