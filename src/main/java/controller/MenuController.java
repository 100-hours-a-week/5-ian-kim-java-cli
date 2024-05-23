package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.response.DisplayMenuResponse;
import controller.response.Response;
import model.Category;
import model.Item;
import service.MenuService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static util.InputHandler.*;

public class MenuController {
    private final MenuService menuService;
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
//    public void itemRegister() {
//        Optional<Category> findCategory = selectCategory();
//        if (findCategory.isPresent()) {
//            Category selectedCategory = findCategory.get();
//            String itemName = inputItemName();
//            Integer itemPrice = inputItemPrice();
//            Integer itemStock = inputItemStock();
//            if (itemName == null || itemPrice == null || itemStock == null) return;
//
//            Item item = new Item(itemName, itemPrice, itemStock);
//            selectedCategory.addItem(item);
//
//            System.out.println("아이템이 성공적으로 등록되었습니다.");
//        } else {
//            System.out.println("===========  해당하는 카테고리가 없습니다.  ===========");
//        }
//    }
//
//    private Optional<Category> selectCategory() {
//        System.out.printf("%-20s %-20s%n", "카테고리 ID", "카테고리 이름");
//        System.out.println("----------------------------------------------------");
//        for (Category category : categories) {
//            System.out.printf("%-20d %-20s%n", category.getIdCounter() / 100, category.getCategoryName());
//        }
//        int categorySelect = getIntInput("카테고리의 ID를 선택하세요 : ");
//        return categories.stream().filter(category -> category.getIdCounter() / 100 == categorySelect).findFirst();
//    }
//
//    public void itemDelete() {
//        displayMenu();
//        int itemId = getIntInput("삭제할 아이템의 ID를 입력하세요 : ");
//        Optional<Item> findItem = findItemById(itemId);
//
//        System.out.println(findItem.toString());
//        if (findItem.isPresent()) {
//            Item item = findItem.get();
//            item.getCategory().removeItem(item);
//            System.out.println("아이템이 성공적으로 삭제되었습니다.");
//        } else {
//            System.out.println("===========  해당하는 아이템이 없습니다.  ===========");
//
//        }
//    }
//
//
//    public void itemUpdate() {
//        displayMenu();
//        int itemId = getIntInput("수정할 아이템의 ID를 입력하세요 : ");
//        Optional<Item> findItem = findItemById(itemId);
//
//        if (findItem.isPresent()) {
//            Item item = findItem.get();
//            String itemName = inputItemName();
//            Integer itemPrice = inputItemPrice();
//            Integer itemStock = inputItemStock();
//            if (itemName == null || itemPrice == null || itemStock == null) return;
//
//            item.setName(itemName);
//            item.setPrice(itemPrice);
//            item.setStock(itemStock);
//
//            System.out.println("아이템이 성공적으로 수정되었습니다.");
//        } else {
//            System.out.println("===========  해당하는 아이템이 없습니다.  ===========");
//        }
//    }
//
//    private String inputItemName() {
//        String itemName = getStringInput("아이템의 이름을 입력하세요: ");
//        if (itemName.length() > 20) {
//            System.out.println("아이템의 이름은 20자 이하여야 합니다.");
//            return null;
//        }
//        return itemName;
//    }
//
//    private Integer inputItemPrice() {          //
//        try {
//            int itemPrice = getIntInput("아이템의 가격을 입력하세요: ");
//            if (itemPrice < 0) {
//                System.out.println("가격은 0원 이상이어야 합니다.");
//                return null;
//            }
//            return itemPrice;
//        } catch (NumberFormatException e) {
//            System.out.println("가격은 숫자로 입력해주세요.");
//            return null;
//        }
//    }
//
//    private Integer inputItemStock() {
//        try {
//            int itemStock = getIntInput("아이템의 재고를 입력하세요: ");
//            if (itemStock < 0) {
//                System.out.println("재고는 0개 이상이어야 합니다.");
//                return null;
//            }
//            return itemStock;
//        } catch (NumberFormatException e) {
//            System.out.println("재고는 숫자로 입력해주세요.");
//            return null;
//        }
//    }
//
//
//    public Optional<Item> findItemById(int itemId) {
//        return categories.stream()
//                .flatMap(category -> category.getItems().stream())
//                .filter(item -> item.getId() == itemId)
//                .findFirst();
//    }

    public Response<DisplayMenuResponse> getCategories() {
        List<Category> categories = menuService.getCategories();
        DisplayMenuResponse displayMenuResponse = new DisplayMenuResponse();

        for (Category category : categories) {
            DisplayMenuResponse.CategoryInfo categoryInfo = new DisplayMenuResponse.CategoryInfo();
            categoryInfo.setCategoryName(category.getCategoryName());

            List<Item> items = category.getItems().stream()
                    .filter(item -> item.getStock() > 0)
                    .collect(Collectors.toList());
            categoryInfo.setItems(items);

            displayMenuResponse.getCategoryInfos().add(categoryInfo);
        }
        return Response.success(displayMenuResponse);
    }
}
