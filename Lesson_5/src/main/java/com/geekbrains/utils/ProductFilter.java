package com.geekbrains.utils;

import com.geekbrains.entites.Product;
import com.geekbrains.repositories.specifications.ProductSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Product> spec;
    private StringBuilder filterDefinition;

    public ProductFilter(Map<String, String> map) {
        this.spec = Specification.where(null);
        this.filterDefinition = new StringBuilder();
        if (map.containsKey("min_price") && !map.get("min_price").isEmpty()) {
            int minPrice = Integer.parseInt(map.get("min_price"));
            spec = spec.and(ProductSpecifications.priceGEThan(minPrice));
            filterDefinition.append("&min_price=").append(minPrice);
        }
        if (map.containsKey("max_price") && !map.get("max_price").isEmpty()) {
            int maxPrice = Integer.parseInt(map.get("max_price"));
            spec = spec.and(ProductSpecifications.priceLEThan(maxPrice));
            filterDefinition.append("&max_price=").append(maxPrice);
        }
        if (map.containsKey("category") && !map.get("category").isEmpty()) {
            Long category = Long.parseLong(map.get("category"));
            spec = spec.and(ProductSpecifications.categoryEQ(category));
            filterDefinition.append("&category=").append(category);
        }
        if (map.containsKey("direction") && !map.get("direction").isEmpty()) {
            String direction = map.get("direction");
            filterDefinition.append("&direction=").append(direction);
        }
    }
}
