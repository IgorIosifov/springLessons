package com.geekbrains.controllers;

import com.geekbrains.entities.Product;
import com.geekbrains.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


// http://localhost:8189/app/products/...
@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    // GET http://localhost:8189/app/products/show_form
    @GetMapping("/show_form")
    public String showSimpleForm(Model model) {
        Product product = new Product();
        product.setTitle("Bread");
        model.addAttribute("product", product);
        return "product_form";
    }

    // POST http://localhost:8189/app/products/process_form
    @PostMapping("/process_form")
    public String processForm(@ModelAttribute("product") Product product) {
        productService.insert(product);
        return "redirect:/products/show_all_products";
    }

    // http://localhost:8189/app/products/info/1
    @RequestMapping(path = "/info/{id}", method = RequestMethod.GET)
//    @ResponseBody
    public String getProductById(@PathVariable Long id, Model model) {
        Product productById = productService.findById(id).get();
        model.addAttribute(productById);
        return "product_form_result";
    }

    // http://localhost:8189/app/products/show_all_products
    @RequestMapping(path = "/show_all_products", method = RequestMethod.GET)
    public String showAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "all_products";
    }
}