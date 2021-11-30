package az.mycompany.bookstore.controller;


import az.mycompany.bookstore.model.BookResponse;
import az.mycompany.bookstore.model.NewBook;
import az.mycompany.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeBookController {

    private final BookService service;

    @GetMapping(value = {"/","/index","/home"})
    public String home(Model model){
        model.addAttribute("books", getAllBooks());
       model.addAttribute("productsCount", 1);
      model.addAttribute("categories",1);
        return "home";
    }

    public List<BookResponse> getAllBooks(){
       return service.getAllBooks();
    }

    public Page<NewBook>get(Pageable pageable){
        return service.get(pageable);
    }

}
