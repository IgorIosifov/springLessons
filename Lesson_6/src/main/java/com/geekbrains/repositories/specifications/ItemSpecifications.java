package com.geekbrains.repositories.specifications;

import com.geekbrains.entites.Item;
import org.springframework.data.jpa.domain.Specification;

public class ItemSpecifications {
    public static Specification<Item> priceGEThan(int value) {
        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value);
    }

    public static Specification<Item> priceLEThan(int value) {
        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), value);
    }

    public static Specification<Item> categoryIdEquals(Long catId) {
//        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), catId); // тоже работает
        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("category").get("id"), catId);
    }
}
