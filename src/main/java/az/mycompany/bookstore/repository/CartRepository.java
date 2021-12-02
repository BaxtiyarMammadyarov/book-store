package az.mycompany.bookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart,Long> {


    @Query("{userId:'?0'}")
    Optional<Cart> findByUserId(Long id);
    @Query("{userId:'?0'}")
    Cart getByUserId(Long userId);
    @Query(value = "{userId:'?0'}",delete = true)
    void deleteByUserId(Long userId);
    @Query("{userId:'?0'}")
    Boolean existsByUserId(Long id);
}
