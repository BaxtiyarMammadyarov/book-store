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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookSellServiceImpl implements BookSellService {
    private final BookClient client;
    private final CartRepository repository;
    private final NextSequenceService sequenceService;

    @Override
    public void addBookToCart(String isbn13, long id) {
        log.info("Started add");
        List<Book> books =new ArrayList<>();
        Cart cart = repository.findByUserId(id).orElse(null);
        if (cart!=null) {
            log.info("if started");
            boolean bool = false;
            for (Book book : cart.getBooks()) {
                log.info("for started");
                if (book.getIsbn13().equals(isbn13)) {
                    book.setCount(book.getCount() + 1);
                } else {
                    bool = true;
                }
            }
            if (bool) {
                Book book = client.getBookByIsbn13(isbn13).orElseThrow(() -> new BookNotFoundException("book not found"));
                books.add(book);
            }
            cart.setBooks(books);
            repository.insert(cart);
        } else {
            Book book = client.getBookByIsbn13(isbn13).orElseThrow(() -> new BookNotFoundException("book not found"));
             books.add(book);
             cart = Cart.builder()
                     .id((long) sequenceService.getNextSequence("customSequences"))
                    .userId(id)
                    .books(books)
                    .build();
            repository.insert(cart);
        }


    }


    @Override
    public void removeBookFromCart(String isbn13, long id) {
        Cart cart = repository.findByUserId(id).orElseThrow(() -> new CartNotFoundException("cart not found"));
        List<Book>books= cart.getBooks();
        int index=0;
        for (Book book:books) {
            index++;
            if(book.getCount()==1&&book.getIsbn13().equals(isbn13)){
                books.remove(index-1);
                break;
            }
            else if(book.getCount()>1&&book.getIsbn13().equals(isbn13)) {
                book.setCount(book.getCount()-1);
            }
        }
        cart.setBooks(books);
        repository.insert(cart);
    }

    @Override
    public void clearBooksInCart(long userId) {
        repository.deleteByUserId(userId);
    }

    @Override
    public List<BookSellResponse> getAllBooks(long userId) {
        log.info("service userId"+userId);
        Cart cart =repository.findByUserId(userId).orElseThrow(() -> new CartNotFoundException("cart not found"));
        log.info(cart);
        List<BookSellResponse>responseList= cart.getBooks().stream().map(this::convertDocumentToModel).collect(Collectors.toList());
        System.out.println(responseList);
        return responseList;
    }

    @Override
    public BigDecimal totalPrice(long userId) {
        BigDecimal bigDecimal = new BigDecimal(0.0);
       Cart cart =repository.getByUserId(userId);
       cart.getBooks().stream()
               .forEach(book -> bigDecimal
                       .add(BigDecimal.valueOf(
                               Double.parseDouble(book.getPrice().substring(1))*book.getCount())));
        return bigDecimal;
    }

    @Override
    public void cartCheckout(long userId) {
        repository.deleteByUserId(userId);
    }

    private BookSellResponse convertDocumentToModel(Book book) {
        return BookSellResponse.builder()
                .title(book.getTitle())
                .subtitle(book.getSubtitle())
                .image(book.getImage())
                .isbn13(book.getIsbn13())
                .price(book.getPrice())
                .url(book.getUrl())
                .count(book.getCount())
                .build();
    }



}
