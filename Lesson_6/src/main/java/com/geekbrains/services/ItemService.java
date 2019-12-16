package com.geekbrains.services;

import com.geekbrains.entites.Item;
import com.geekbrains.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Page<Item> findAll(Specification<Item> spec, Pageable pageable) {
        return itemRepository.findAll(spec, pageable);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).get();
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public Item replaceItem(Item item){
        if (findById(item.getId())!=null) {
            deleteItemById(item.getId());
            save(item);
        }
        return item;
    }

    public void deleteAllItems() {
        itemRepository.deleteAll();
    }

    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }

}
