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


    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("books",service.getAllBooks());
        model.addAttribute("totalPrice",service.totalPrice());

        return "cart";
    }

    @GetMapping("/cart/add/{isbn13}")
    public String addProductToCart(@PathVariable("isbn13") String isbn13){
        log.info("start controller"+ isbn13);
         service.addBookToCart(isbn13);
        return "redirect:/home";
    }

    @GetMapping("/cart/remove/{isbn13}")
    public String removeBookFromCart(@PathVariable("isbn13") String isbn13 ){
         service.removeBookFromCart(isbn13);
        return "redirect:/cart";
    }
    @GetMapping("/cart/clear")
    public String clearBooksInCart(){
      service.clearBooksInCart();
        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String cartCheckout(){
     service.cartCheckout();

        return "redirect:/cart";
    }
    @GetMapping("/test")
    public void getAll(){
        service.getAll();
    }
}
