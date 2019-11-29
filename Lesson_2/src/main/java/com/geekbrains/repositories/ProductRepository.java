package com.geekbrains.repositories;

import com.geekbrains.entities.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> products;

    @SuppressWarnings("Duplicates")
    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Bread", new BigDecimal(15.0)));
        products.add(new Product(2L, "Coffee", new BigDecimal(80.0)));
        products.add(new Product(3L, "Onion", new BigDecimal(5.0)));
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> findOneById(Long id) {
        for (Product s : products) {
            if (s.getId().equals(id)) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    public boolean existById(Long id) {
        return products.stream().anyMatch(s -> s.getId().equals(id));
    }

    public void insert(Product product) {
        if (existById(product.getId())) {
            throw new RuntimeException("Product with id: " + product.getId() + " is already exists");
        }
        long nextId = (long) (products.size() + 1);
        product.setId(nextId);
        products.add(product);
    }
}