package az.mycompany.bookstore.service;


import az.mycompany.bookstore.model.BookSellResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface BookSellService {

    void addBookToCart(String isbn13,long id);


    void removeBookFromCart(String isbn13,long id);

    void clearBooksInCart(long id);

    List<BookSellResponse> getAllBooks(long id);

    BigDecimal totalPrice(long userId);

    void cartCheckout(long id);
}
