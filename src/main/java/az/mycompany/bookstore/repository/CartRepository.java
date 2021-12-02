package az.mycompany.bookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart,Long> {

    Cart getById(long id);
}
