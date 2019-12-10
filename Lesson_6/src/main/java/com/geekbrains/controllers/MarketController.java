package com.geekbrains.controllers;

import com.geekbrains.entites.Category;
import com.geekbrains.entites.Item;
import com.geekbrains.services.CategoryService;
import com.geekbrains.services.ItemService;
import com.geekbrains.utils.ItemFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class MarketController {
    private ItemService itemService;
    private CategoryService categoryService;
    private Cart cart;

    @Autowired
    public MarketController(ItemService itemService, CategoryService categoryService, Cart cart) {
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.cart = cart;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        int pageIndex = 0;
        if (params.containsKey("p")) {
            pageIndex = Integer.parseInt(params.get("p")) - 1;
        }
        Pageable pageRequest = PageRequest.of(pageIndex, 10);
        ItemFilter itemFilter = new ItemFilter(params);
        Page<Item> page = itemService.findAll(itemFilter.getSpec(), pageRequest);

        List<Category> categories = categoryService.getAll();
        model.addAttribute("filtersDef", itemFilter.getFilterDefinition());
        model.addAttribute("categories", categories);
        model.addAttribute("page", page);

        return "index";
    }

    @GetMapping("/edit/{id}")
    public String editItemForm(Model model, @PathVariable Long id) {
        Item item = itemService.findById(id);
        List<Category> categories = categoryService.getAll();
        model.addAttribute("item", item);
        model.addAttribute("categories", categories);
        return "edit_item";
    }

    @PostMapping("/edit")
    public String saveItem(@ModelAttribute(name = "item") Item item) {
        itemService.save(item);
        return "redirect:/";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(Model model, @PathVariable Long id) {
        model.addAttribute("id", id);
        cart.addToCart(id);
        return "redirect:/";
    }

    @GetMapping ("/cart")
    public String cart(Model model) {
        model.addAttribute("cart", cart);
        model.addAttribute("Items", cart.allItemsInCart());
        return "cart";
    }
    }
