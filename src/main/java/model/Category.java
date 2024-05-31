package model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int idCounter = 0;
    private String categoryName;
    private List<Item> items = new ArrayList<Item>();

    public Category(String categoryName, int idCounter) {
        this.categoryName = categoryName;
        this.idCounter = idCounter;
    }


    public void addItem(Item item) {
        item.setId(++idCounter);
        items.add(item);
        item.setCategory(this);
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.setCategory(null);
    }

    public int getIdCounter() {
        return idCounter;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Item> getItems() {
        return items;
    }
}
