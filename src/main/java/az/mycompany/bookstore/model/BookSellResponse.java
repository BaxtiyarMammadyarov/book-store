package az.mycompany.bookstore.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookSellResponse {
    String title;
    String subtitle;
    String isbn13;
    String price;
    String image;
    String url;
    int count;

}
