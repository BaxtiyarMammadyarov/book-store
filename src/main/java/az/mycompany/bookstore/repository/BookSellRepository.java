package az.mycompany.bookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookSellRepository extends MongoRepository<BookSell,Long> {
    boolean existsByIsbn13(String isbn13);


    BookSell findByIsbn13(String isbn13);
}
