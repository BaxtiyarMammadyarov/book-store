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
import org.springframework.ui.Model;


import java.awt.image.RescaleOp;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Log4j2
public class BookServiceImpl implements BookService {

    final BookClient client;
    final PageValidation pageValidation;



    @Override
    public void getAllBooks(int pageNumber, int size,Model model) {
        List<BookResponse> responses=client.getAll().getBooks();
        Page<BookResponse> bookPage = pageValidation.getPage(responses,pageNumber,size);
        modelInit(bookPage, model);

    }

    @Override
    public void getSearch(String search, int pageNumber, int size, Model model) {
      List<BookResponse>responses=  client.getBookSearch(search).getBooks();

        Page<BookResponse> bookPage = pageValidation.getPage(responses,pageNumber,size);
        modelInit(bookPage, model);
    }
    private void modelInit(Page page, Model model) {
        model.addAttribute("bookPage", page);

        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
            model.addAttribute("userId",'1');
        }
    }
}
