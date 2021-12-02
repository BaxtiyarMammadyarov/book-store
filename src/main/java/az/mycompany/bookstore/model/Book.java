package az.mycompany.bookstore.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;



@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    String error;
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
    Pdf pdf;
    int count;
}
