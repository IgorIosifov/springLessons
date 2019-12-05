package com.geekbrains.repositories.specifications;

import com.geekbrains.entites.Category;
import com.geekbrains.entites.Product;
import com.geekbrains.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;


public class ProductSpecifications {

    public static Specification<Product> priceGEThan(int value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), value);
    }
    public static Specification<Product> priceLEThan(int value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), value);
    }

    public static Specification<Product> categoryEQ(Long value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), value);
    }
}
