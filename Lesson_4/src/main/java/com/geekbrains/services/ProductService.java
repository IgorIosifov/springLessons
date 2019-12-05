package com.geekbrains.services;

import com.geekbrains.entities.Product;
import com.geekbrains.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllItems() {
        return productRepository.findAll();
    }


    public Page<Product> getAllItemsWithPaginationAndSorting(Specification spec, int currentPage, int pageSize, Direction direction, String sortBy) {

        return productRepository.findAll(spec, PageRequest.of(currentPage, pageSize, direction, sortBy));
    }
}
