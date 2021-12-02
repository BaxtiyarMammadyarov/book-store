package az.mycompany.bookstore.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customSequences")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomSequences {
    @Id
    private String id;
    private int seq;

}
