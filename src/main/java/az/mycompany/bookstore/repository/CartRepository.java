package az.mycompany.bookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CartRepository extends MongoRepository<Cart, String> {

    @Query("{userId:'?0'}")
    List<Cart> findByUserId(@Param("userId") Long id);


    @Query("{userId:'?0'}")
    List<Cart> getByUserId(@Param("userId") Long userId);

    @Query(value = "{userId:'?0'}", delete = true)
    void deleteByUserId(@Param("userId") Long userId);

    @Query("{userId:'?0'}")
    Boolean existsByUserId(@Param("userId") Long id);

//    @Query("{userId:'?0'},{isbn13:'?1'}")
    Cart findByIdAndUserId(String isbn13,long userId);
}
