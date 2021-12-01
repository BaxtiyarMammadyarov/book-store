package az.mycompany.bookstore.service.impl;

import az.mycompany.bookstore.client.BookClient;
import az.mycompany.bookstore.exception.BookNotFoundException;
import az.mycompany.bookstore.model.Book;
import az.mycompany.bookstore.model.BookResponse;
import az.mycompany.bookstore.model.BookSellResponse;
import az.mycompany.bookstore.repository.BookSell;
import az.mycompany.bookstore.repository.BookSellRepository;
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
    private final BookSellRepository repository;

    @Override
    public void addBookToCart(String isbn13) {
        log.info("Started add");
       if(repository.existsByIsbn13(isbn13)){
            BookSell bookSell=repository.findByIsbn13(isbn13);
            bookSell.setCount(bookSell.getCount()+1);
            repository.save(bookSell);
        }else {
            Book book= client.getBookByIsbn13(isbn13).orElseThrow(() -> new BookNotFoundException("book not found"));
            BookSell bookSell=BookSell.builder()
                    .id(1l)
                    .title(book.getTitle())
                    .authors(book.getAuthors())
                    .isbn10(book.getIsbn10())
                    .isbn13(book.getIsbn13())
                    .image(book.getImage())
                    .desc(book.getDesc())
                    .pages(book.getPages())
                    .price(book.getPrice())
                    .publisher(book.getPublisher())
                    .subtitle(book.getSubtitle())
                    .url(book.getUrl())
                    .rating(book.getRating())
                    .year(book.getYear())
                    .count(1)
                    .build();
            repository.save(bookSell);

        }
    }

    @Override
    public void getAll() {
        repository.findAll().stream().forEach(bookSell -> System.out.println(bookSell));
    }

    @Override
    public void removeBookFromCart(String isbn13) {
        BookSell bookSell=repository.findByIsbn13(isbn13);
        if(bookSell.getCount()>1){
            bookSell.setCount(bookSell.getCount()-1);
            repository.save(bookSell);
        }else {
            repository.delete(bookSell);
        }
    }

    @Override
    public void clearBooksInCart() {
        repository.deleteAll();
    }

    @Override
    public List<BookSellResponse> getAllBooks() {
      return repository.findAll().stream().map(this::convertDocumentToModel).collect(Collectors.toList());
    }

    @Override
    public BigDecimal totalPrice() {
        BigDecimal bigDecimal=new BigDecimal(0.0);
        repository
                .findAll()
                .stream()
                .forEach(bookSell -> bigDecimal
                        .add(BigDecimal.valueOf(Double.parseDouble(bookSell.getPrice().substring(1))*bookSell.getCount())));
        return bigDecimal;
    }

    @Override
    public void cartCheckout() {
        repository.deleteAll();
    }

    private BookSellResponse convertDocumentToModel(BookSell bookSell){
        return BookSellResponse.builder()
                .title(bookSell.getTitle())
                .subtitle(bookSell.getSubtitle())
                .image(bookSell.getImage())
                .isbn13(bookSell.getIsbn13())
                .price(bookSell.getPrice())
                .url(bookSell.getUrl())
                .count(bookSell.getCount())
                .build();
    }


}
