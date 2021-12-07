package az.mycompany.bookstore.repository;

import az.mycompany.bookstore.model.Book;
import az.mycompany.bookstore.model.Pdf;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart implements Serializable {
    @Id
    String id;
    @Field(name = "userId")
    Long userId;
    @Field(name = "error")
    String error;
    @Field(name = "title")
    String title;
    @Field(name = "subtitle")
    String subtitle;
    @Field(name = "authors")
    String authors;
    @Field(name = "publisher")
    String publisher;
    @Field(name = "isbn10")
    String isbn10;
    @Field(name = "isbn13")
    String isbn13;
    @Field(name = "pages")
    String pages;
    @Field(name = "year")
    String year;
    @Field(name = "rating")
    String rating;
    @Field(name = "desc")
    String desc;
    @Field(name = "price")
    String price;
    @Field(name = "image")
    String image;
    @Field(name = "url")
    String url;
    @Field(name = "pdf")
    Pdf pdf;
    @Field(name = "count")
    int count;


}
