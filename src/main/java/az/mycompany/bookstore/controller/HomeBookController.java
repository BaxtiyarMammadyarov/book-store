package az.mycompany.bookstore.controller;


import az.mycompany.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@Log4j2
public class HomeBookController {

    private final BookService service;

    @GetMapping(value = {"/", "/index", "/home"})
    public String getAllBook(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                             @RequestParam(value = "size", required = false, defaultValue = "5") int size, Model model) {

        log.info("started getAllBook method");
        service.getAllBooks(pageNumber, size, model);
        log.info("ended getAllBook method");

        return "home";
    }

    @GetMapping(value = "/search")
    public String getSearch(@RequestParam(value = "search") String search,
                            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                            @RequestParam(value = "size", required = false, defaultValue = "5") int size, Model model) {

        log.info("start getSearch method");
        service.getSearch(search, pageNumber, size, model);
        log.info("ended getSearch method");
        return "home";
    }

}



