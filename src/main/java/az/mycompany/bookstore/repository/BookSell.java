package az.mycompany.bookstore.repository;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookSell {

    Long id;
    String title;
    String subtitle;
    String authors;
    String publisher;
    String isbn10;
    String isbn13;
    String pages;
    String year;
    String rating;
    String desc;
    String price;
    String image;
    String url;
    int count;
}
