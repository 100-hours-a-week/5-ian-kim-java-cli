package controller.response;

import model.Item;

import java.util.ArrayList;
import java.util.List;

public class DisplayMenuResponse {
    private List<CategoryInfo> categoryInfos = new ArrayList<>();

    public List<CategoryInfo> getCategoryInfos() {
        return categoryInfos;
    }

    public static class CategoryInfo {
        private String categoryName;
        private List<Item> items;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }
    }
}