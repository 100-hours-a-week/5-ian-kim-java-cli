package service;

import controller.request.ItemInfoRequest;
import controller.request.UpdateItemInfoRequest;
import model.Category;
import model.Item;
import util.InputHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static util.InputHandler.*;

public class MenuService {
    Category c100 = new Category("튀김류", 100);
    Category c200 = new Category("탕류", 200);
    Category c300 = new Category("주류", 300);
    Category c400 = new Category("음료", 400);
    Category c500 = new Category("사이드메뉴", 500);

    List<Category> categories = new ArrayList<>();

    public MenuService() {
        categories.add(c100);
        categories.add(c200);
        categories.add(c300);
        categories.add(c400);
        categories.add(c500);

        // 카테고리에 항목 추가
        c100.addItem(new Item("치킨 너겟", 10000, 20)); // 튀김류
        c100.addItem(new Item("감자 튀김", 8000, 20)); // 튀김류
        c200.addItem(new Item("된장찌개", 12000, 20)); // 탕류류
        c200.addItem(new Item("김치찌개", 10000, 20)); // 탕류류
        c200.addItem(new Item("부대찌개", 15000, 20)); // 탕류류
        c300.addItem(new Item("맥주", 5000, 20)); // 주류
        c300.addItem(new Item("소주", 4000, 20)); // 주류
        c300.addItem(new Item("위스키", 10000, 20)); // 주류
        c300.addItem(new Item("와인", 15000, 20)); // 주류
        c400.addItem(new Item("콜라", 2000, 20)); // 음료
        c400.addItem(new Item("오렌지 주스", 3000, 20)); // 음료
        c400.addItem(new Item("레몬에이드", 3000, 20)); // 음료
        c400.addItem(new Item("아메리카노", 4000, 20)); // 음료
        c500.addItem(new Item("감자 튀김", 5000, 20)); // 사이드메뉴
        c500.addItem(new Item("치즈스틱", 6000, 20)); // 사이드메뉴
        c500.addItem(new Item("감자샐러드", 7000, 20)); // 사이드메뉴
        c500.addItem(new Item("계란말이", 6000, 20)); // 사이드메뉴
    }

    public void itemRegister(ItemInfoRequest itemInfoRequest) {
        try {
            Category category = findCategoryById(itemInfoRequest.getCategoryNumber())
                    .orElseThrow(() -> new IllegalArgumentException("해당하는 카테고리가 없습니다."));
            Item item = new Item(itemInfoRequest.getItemName(), itemInfoRequest.getItemPrice(), itemInfoRequest.getItemStock());

            category.addItem(item);

            System.out.println("아이템이 성공적으로 등록되었습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void itemDelete(int itemId) {
        try {
            Item findItem = findItemById(itemId)
                    .orElseThrow(() -> new IllegalArgumentException("해당하는 아이템이 없습니다."));
            System.out.println(findItem.toString());
            findItem.getCategory().removeItem(findItem);
            System.out.println("아이템이 성공적으로 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void itemUpdate(UpdateItemInfoRequest itemInfoRequest) {
        try {
            Item findItem = findItemById(itemInfoRequest.getItemNumber())
                    .orElseThrow(() -> new IllegalArgumentException("해당하는 아이템이 없습니다."));

            findItem.setName(itemInfoRequest.getItemName());
            findItem.setPrice(itemInfoRequest.getItemPrice());
            findItem.setStock(itemInfoRequest.getItemStock());

            System.out.println("아이템이 성공적으로 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public Optional<Item> findItemById(int itemId) {
        return categories.stream()
                .flatMap(category -> category.getItems().stream())
                .filter(item -> item.getId() == itemId)
                .findFirst();
    }

    public Optional<Category> findCategoryById(int categoryId) {
        return categories.stream()
                .filter(category -> category.getIdCounter() / 100 == categoryId)
                .findFirst();
    }

    public List<Category> getCategories() {
        return categories;
    }
}
