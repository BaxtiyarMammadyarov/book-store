package az.mycompany.bookstore.config;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PageableQueryEncoder implements Encoder {

    private final Encoder delegate;

    PageableQueryEncoder(final Encoder delegate) {
        this.delegate = delegate;
    }

    @Override
    public void encode(final Object object, final Type bodyType, final RequestTemplate template) throws EncodeException {

        if (object instanceof Pageable) {
            final Pageable pageable = (Pageable) object;
            template.query("page", pageable.getPageNumber() + "");
            template.query("size", pageable.getPageSize() + "");

            if (pageable.getSort() != null) {
                final Collection<String> existingSorts = template.queries().get("sort");
                final List<String> sortQueries = existingSorts != null ? new ArrayList<>(existingSorts) : new ArrayList<>();
                for (final Sort.Order order : pageable.getSort()) {
                    sortQueries.add(order.getProperty() + "," + order.getDirection());
                }
                template.query("sort", sortQueries);
            }

        } else {
            this.delegate.encode(object, bodyType, template);
        }
    }
}
