package az.mycompany.bookstore.validation;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;


import java.util.Collections;
import java.util.List;

@Component
public class PageValidation<T> {

 public Page<T> getPage(List<T> list, int pageNumber, int size){
     int pageSize = size;
     int currentPage =pageNumber;
     int startItem = currentPage * pageSize;

     List<T> page;
     if (list.size() < startItem) {
         page = Collections.emptyList();
     } else {
         int toIndex = Math.min(startItem + pageSize, list.size());
         page = list.subList(startItem, toIndex);
     }

     Page<T> bookPage
             = new PageImpl<T>(page, PageRequest.of(currentPage, pageSize),
             list.size());

     return bookPage;


 }
}
