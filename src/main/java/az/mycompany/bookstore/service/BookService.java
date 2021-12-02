package az.mycompany.bookstore.service;


import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public interface BookService {

    void getAllBooks(int pageNumber, int size, Model model);

    void getSearch(String search, int pageNumber, int size, Model model);
}
