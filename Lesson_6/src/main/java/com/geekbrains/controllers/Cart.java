package com.geekbrains.controllers;

import com.geekbrains.entites.Item;
import com.geekbrains.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class Cart {

    private Map<Item, Integer> cart;

    @PostConstruct
    public void init() {
        cart = new ConcurrentHashMap<>();
    }

    private ItemService itemService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public void addToCart(Long id) {

        Item itemById = itemService.findById(id);
        int quantity = cart.getOrDefault(itemById,0);
        if (quantity == 0) {
            cart.put(itemById, ++quantity);
        } else {
            cart.replace(itemById, ++quantity);
        }

    }

    public Set<Item> allItemsInCart() {
        return cart.keySet();
    }

    public int totalCost() {
        int totalCost = 0;
        for (Item item : cart.keySet()) {
            totalCost += item.getPrice() * cart.getOrDefault(item,0);
        }
        return totalCost;
    }
    public Integer get(Item item ){
        return cart.getOrDefault(item,0);

    }
}





