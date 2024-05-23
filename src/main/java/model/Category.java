package model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int idCouneter = 0;
    private String categoryName;
    private List<Item> items = new ArrayList<Item>();

    public Category(String categoryName, int idCounter) {
        this.categoryName = categoryName;
        this.idCouneter = idCounter;
    }


    public void addItem(Item item) {
        item.setId(++idCouneter);
        items.add(item);
        item.setCategory(this);
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.setCategory(null);
    }

    public int getIdCounter() {
        return idCouneter;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Item> getItems() {
        return items;
    }
}
