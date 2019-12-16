package com.geekbrains.controllers;

import com.geekbrains.entites.Item;
import com.geekbrains.services.CategoryService;
import com.geekbrains.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ControllerREST {
    private ItemService itemService;
    private CategoryService categoryService;


    @Autowired
    public ControllerREST(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;

    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.findById(id);
    }

    @PostMapping
    public Item saveItem(@RequestBody Item item) {
        item.setId((long) itemService.findAll().size() + 1);
        itemService.save(item);
        return item;
    }

    @PutMapping
    public Item updateItem(@RequestBody Item item) {
        return itemService.replaceItem(item);
    }

    @DeleteMapping
    public void deleteAllItems() {
        itemService.deleteAllItems();
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable Long id) {
        itemService.deleteItemById(id);
    }
}
