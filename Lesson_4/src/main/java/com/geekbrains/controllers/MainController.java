package com.geekbrains.controllers;

import com.geekbrains.entities.Product;
import com.geekbrains.repositories.ProductSpecifications;
import com.geekbrains.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

@Controller
public class MainController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    @ResponseBody
    public List<Product> index() {
        return productService.getAllItems();
    }

    @GetMapping("/products")
    @ResponseBody
    public List<Product> getAllItems() {
        return productService.getAllItems();
    }

    @GetMapping("/filteringAndPaging")
    public String pagingExample(Model model,
                                @RequestParam(required = false, name = "min_price") Integer minPrice,
                                @RequestParam(required = false, name = "max_price") Integer maxPrice,
                                @RequestParam(required = false, name = "word") String word
    ) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterThanOrEq(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLesserThanOrEq(maxPrice));
        }
        if (word != null) {
            spec = spec.and(ProductSpecifications.titleContains(word));
        }

        Page<Product> page = productService.getAllItemsWithPaginationAndSorting(spec, 0, 5, Sort.Direction.ASC, "title");


        model.addAttribute("products", page.getContent());
        model.addAttribute("productsCount", page.getTotalElements());
        return "list";
    }
}
