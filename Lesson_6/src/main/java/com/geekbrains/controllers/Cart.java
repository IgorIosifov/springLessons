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
        int quantity = getItemFromMap(itemById);
        if (quantity == 0) {
            cart.put(itemById, ++quantity);
        } else {
            removeItem(itemById);
            cart.put(itemById, ++quantity);
        }

    }

    public Set<Item> allItemsInCart() {
        return cart.keySet();
    }

    public int getItemFromMap(Item item) {
        for (Item it : cart.keySet()) {
            if (it.getId().equals(item.getId())) {
                return cart.get(it);
            }
        }
        return 0;
    }

    public void removeItem(Item item) {
        for (Item it : cart.keySet()) {
            if (it.getId().equals(item.getId())) {
                cart.remove(it);
            }
        }
    }

    public int totalCost() {
        int totalCost = 0;
        for (Item item : cart.keySet()) {
            totalCost += item.getPrice() * getItemFromMap(item);
        }
        return totalCost;
    }
}





