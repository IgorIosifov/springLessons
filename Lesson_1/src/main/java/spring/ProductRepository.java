package spring;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Bread", new BigDecimal(15.0)));
        products.add(new Product(2L, "Coffee", new BigDecimal(80.0)));
        products.add(new Product(3L, "Onion", new BigDecimal(5.0)));
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public void insert(Product product) {
        products.add(product);
    }

    public Product getProductByID(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

}

