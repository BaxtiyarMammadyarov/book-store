package az.mycompany.bookstore.service.impl;

import az.mycompany.bookstore.client.BookClient;
import az.mycompany.bookstore.exception.BookNotFoundException;
import az.mycompany.bookstore.exception.CartNotFoundException;
import az.mycompany.bookstore.model.Book;
import az.mycompany.bookstore.model.BookSellResponse;

import az.mycompany.bookstore.repository.Cart;
import az.mycompany.bookstore.repository.CartRepository;
import az.mycompany.bookstore.repository.NextSequenceService;
import az.mycompany.bookstore.service.BookSellService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookSellServiceImpl implements BookSellService {
    private final BookClient client;
    private final CartRepository repository;

    @Override
    public void addBookToCart(String isbn13, long id) {
        log.info("Started add");
        boolean bool=false;
        Cart cart;
        if(repository.existsById(isbn13+id)){
            cart=repository.findById(isbn13+id).orElseThrow(() -> new CartNotFoundException("cart not found"));
            cart.setCount(cart.getCount()+1);
        }
        else {
            Book book=client.getBookByIsbn13(isbn13).orElseThrow(() -> new BookNotFoundException("book not found") );
             cart= Cart.builder()
                     .id(isbn13+id)
                    .authors(book.getAuthors())
                    .title(book.getTitle())
                    .subtitle(book.getSubtitle())
                    .desc(book.getDesc())
                    .image(book.getImage())
                    .publisher(book.getPublisher())
                    .pages(book.getPages())
                    .price(book.getPrice())
                    .rating(book.getRating())
                    .userId(id)
                     .isbn13(book.getIsbn13())
                    .isbn10(book.getIsbn10())
                    .url(book.getUrl())
                    .year(book.getYear())
                    .count(1)
                    .error(book.getError())
                    .pdf(book.getPdf())
                    .build();
        }
        repository.save(cart);

    }


    @Override
    public void removeBookFromCart(String isbn13, long id) {
        Cart cart=repository.findById(isbn13+id).orElseThrow(() -> new CartNotFoundException("cart not found"));
        if(cart.getCount()>1){
            cart.setCount(cart.getCount()-1);
            repository.insert(cart);
        }else {
            repository.delete(cart);
        }

    }

    @Override
    public void clearBooksInCart(long userId) {
//        List<Cart> cartList=repository.findAll().stream().filter(cart -> cart.getUserId()==userId).collect(Collectors.toList());
        repository.findAll().stream().filter(cart -> cart.getUserId()==userId).forEach(cart -> repository.delete(cart));
//        repository.deleteAll(cartList);
    }

    @Override
    public List<BookSellResponse> getAllBooks(long userId) {
        log.info("getAllBook method started");
        return repository.findAll().stream().filter(cart -> cart.getCount()>0&&cart.getUserId()==userId).map(this::convertDocumentToModel).collect(Collectors.toList());

    }

    @Override
    public double totalPrice(long userId) {

       List<Cart> cartList=repository.findAll().stream().filter(cart -> cart.getUserId()==userId).collect(Collectors.toList());
       double sum=0.0;
        for (Cart cart:cartList) {
          sum+=Double.parseDouble(cart.getPrice().substring(1));

        }
        return sum;
    }

    @Override
    public void cartCheckout(long userId) {

        repository.deleteByUserId(userId);
    }

    private BookSellResponse convertDocumentToModel(Cart cart) {
        return BookSellResponse.builder()
                .title(cart.getTitle())
                .subtitle(cart.getSubtitle())
                .image(cart.getImage())
                .isbn13(cart.getIsbn13())
                .price(cart.getPrice())
                .url(cart.getUrl())
                .count(cart.getCount())
                .build();
    }




}
