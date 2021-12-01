package az.mycompany.bookstore.controller;


import az.mycompany.bookstore.model.BookResponse;
import az.mycompany.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequiredArgsConstructor
@Log4j2
public class HomeBookController {

    private final BookService service;

    @GetMapping(value = {"/", "/index", "/home"})
    public String getAllBook(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                             @RequestParam(value = "size", required = false, defaultValue = "5") int size, Model model) {

        log.info("-----start controller");
        Page<BookResponse> bookPage = service.getAllBooks(pageNumber, size);
        modelInit(bookPage, model);

        return "home";
    }

    @GetMapping(value = "/search")
    public String getSearch(@RequestParam(value = "search") String search,
                            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                            @RequestParam(value = "size", required = false, defaultValue = "5") int size, Model model) {

        log.info("-----start controller");
        Page<BookResponse> bookPage = service.getSearch(search, pageNumber, size);
        modelInit(bookPage, model);


        return "home";
    }


    private void modelInit(Page page, Model model) {
        model.addAttribute("bookPage", page);

        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }


}
