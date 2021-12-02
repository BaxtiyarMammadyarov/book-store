package az.mycompany.bookstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pdf {
    @JsonProperty("Chapter 2")
   String chapter2;
    @JsonProperty("Chapter 5")
     String chapter5;

}
