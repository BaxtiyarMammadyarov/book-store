package az.mycompany.bookstore.controller;



import az.mycompany.bookstore.service.BookSellService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Log4j2
public class BookSellController {
    private final BookSellService service;


    @GetMapping("/cart/{userId}")
    public String cart(Model model,@PathVariable("userId")long userId){
        log.info("userId=="+userId);
        model.addAttribute("books",service.getAllBooks(userId));
        model.addAttribute("totalPrice",service.totalPrice(userId));

        return "cart";
    }

    @GetMapping("/cart/add/{userId}/{isbn13}")
    public String addProductToCart(@PathVariable("userId")long userId,@PathVariable("isbn13") String isbn13 ){
        log.info("start controller"+ isbn13);
         service.addBookToCart(isbn13, userId);
        return "redirect:/home";
    }

    @GetMapping("/cart/remove/{userId}/{isbn13}")
    public String removeBookFromCart(@PathVariable("userId")long userId,@PathVariable("isbn13") String isbn13 ){
         service.removeBookFromCart(isbn13,userId);
        return "redirect:/cart";
    }
    @GetMapping("/cart/clear/{userId}")
    public String clearBooksInCart(@PathVariable("userId")long userId){
      service.clearBooksInCart(userId);
        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout/{userId}")
    public String cartCheckout(@PathVariable("userId")long userId){
     service.cartCheckout(userId);

        return "redirect:/cart";
    }

}
