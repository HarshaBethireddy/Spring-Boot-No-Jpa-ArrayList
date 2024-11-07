package com.harsha.itemapi.service;

import com.harsha.itemapi.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final List<Item> items = new ArrayList<>();

    public Item addItem(Item item) {
        item.setId(items.size() + 1);
        items.add(item);
        return item;
    }

    public List<Item> getAllItems() {
        return items;
    }

    public Item getItemById(int id) {
        return items.stream().filter(item -> item.getId().equals(id)).findFirst()
                .orElseThrow(() -> new RuntimeException("Item with id: " + id + " not found"));
    }

    public List<Item> getItemsByName(String name) {
        return items.stream().filter(item -> item.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    public Item updateItem(int id, Item newItem) {
        Item existingItem = getItemById(id);

        existingItem.setName(newItem.getName());
        existingItem.setDescription(newItem.getDescription());
        existingItem.setPrice(newItem.getPrice());

        return existingItem;
    }

    public void deleteItem(int id) {
        items.removeIf(item -> item.getId().equals(id));
    }
}
