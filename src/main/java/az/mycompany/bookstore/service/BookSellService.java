package az.mycompany.bookstore.service;


import az.mycompany.bookstore.model.BookSellResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface BookSellService {

    void addBookToCart(String isbn13);
    void getAll();

    void removeBookFromCart(String isbn13);

    void clearBooksInCart();

    List<BookSellResponse> getAllBooks();

    BigDecimal totalPrice();

    void cartCheckout();
}
