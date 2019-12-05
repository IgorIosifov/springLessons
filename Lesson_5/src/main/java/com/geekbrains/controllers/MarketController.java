package com.geekbrains.controllers;

import com.geekbrains.entites.Category;
import com.geekbrains.entites.Product;
import com.geekbrains.services.CategoryService;
import com.geekbrains.services.ProductService;
import com.geekbrains.utils.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MarketController {
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Autowired
    public MarketController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        int pageIndex = 0;
        if (params.containsKey("p")) {
            pageIndex = Integer.parseInt(params.get("p")) - 1;
        }
        Pageable pageRequest = PageRequest.of(pageIndex, 10);

        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> page = productService.findAll(productFilter.getSpec(), pageRequest);

        List<Category> categories = categoryService.findAll();

        model.addAttribute("filtersDef", productFilter.getFilterDefinition());
        model.addAttribute("categories", categories);
        model.addAttribute("page", page);
        return "index";
    }

}