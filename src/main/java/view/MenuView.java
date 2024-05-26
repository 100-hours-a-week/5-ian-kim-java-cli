package view;

import controller.request.CategoryNumberRequest;
import controller.request.ItemCodeRequest;
import controller.request.ItemInfoRequest;
import controller.request.UpdateItemInfoRequest;
import controller.response.DisplayMenuResponse;
import exception.InvalidItemException;
import exception.PayException;
import model.Category;
import model.Item;
import controller.MenuController;
import util.AsciiArt;

import java.util.List;

import static util.InputHandler.getIntInput;
import static util.InputHandler.getStringInput;

public class MenuView {
    private final MenuController menuController;

    public MenuView(MenuController menuController) {
        this.menuController = menuController;
    }

    public void displayMenu() {
        AsciiArt.menuLogo();
        DisplayMenuResponse categories = menuController.getCategoriesWithMenu().getResult();
        for (DisplayMenuResponse.CategoryInfo categoryInfo : categories.getCategoryInfos()) {
            System.out.println(categoryInfo.getCategoryName());
            System.out.println("=================================================");
            for (Item item : categoryInfo.getItems()) {
                if (item.getStock() > 0) {
                    int padding = 20 - item.getName().length() + (countSpaces(item.getName()));
                    System.out.printf("%-10d %-" + padding + "s %5d원\n", item.getId(), item.getName(), item.getPrice());
                }
            }
            System.out.println("================================================\n\n");
        }
    }

    // 공백 크기를 계산해서 그만큼 더해준다.
    // 공백은 1byte로 계산되고 한글은 2byte로 계산된다.
    private int countSpaces(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (Character.isWhitespace(c)) {
                count++;
            }
        }
        return count;
    }

    public void selectedMenuInfo() {
        System.out.printf("%-20s %-20s%n", "카테고리 ID", "카테고리 이름");
        System.out.println("----------------------------------------------------");
        for (Category category : menuController.getCategories().getResult()) {
            System.out.printf("%-20d %-20s%n", category.getIdCounter() / 100, category.getCategoryName());
        }
        int categorySelect = getIntInput("카테고리의 ID를 선택하세요 : ");
        // 아이템 정보 입력
        String itemName = inputItemName();
        Integer itemPrice = inputItemPrice();
        Integer itemStock = inputItemStock();
        if (itemName == null || itemPrice == null || itemStock == null) {
            System.out.println("-----------------  아이템 등록 실패  -----------------");
            return;
        }

        ItemInfoRequest itemInfoRequest = new ItemInfoRequest(categorySelect, itemName, itemPrice, itemStock);
        menuController.itemRegister(itemInfoRequest);
    }

    public void displayDeleteMenu() {
        displayMenu();
        int itemId = getIntInput("삭제할 아이템의 코드를 입력하세요 : ");
        ItemCodeRequest itemCodeRequest = new ItemCodeRequest(itemId);
        menuController.itemDelete(itemCodeRequest.getItemCode());
    }

    public void displayUpdateMenu() {
        displayMenu();
        int itemId = getIntInput("수정할 아이템의 코드를 입력하세요 : ");

        String itemName = inputItemName();
        Integer itemPrice = inputItemPrice();
        Integer itemStock = inputItemStock();
        if (itemName == null || itemPrice == null || itemStock == null) {
            System.out.println("-----------------  아이템 수정 실패  -----------------");
            return;
        }

        UpdateItemInfoRequest itemInfoRequest = new UpdateItemInfoRequest(itemId, itemName, itemPrice, itemStock);
        menuController.itemUpdate(itemInfoRequest);
    }


    private String inputItemName() {
        String itemName = getStringInput("아이템의 이름을 입력하세요: ");
        if (itemName.length() > 20) {
            System.out.println("아이템의 이름은 20자 이하여야 합니다.");
            return null;
        }
        return itemName;
    }

    private Integer inputItemPrice() {
        int itemPrice = getIntInput("아이템의 가격을 입력하세요: ");
        try {
            if (itemPrice < 0) {
                throw new IllegalArgumentException("가격은 0원 이상이어야 합니다.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return itemPrice;
    }

    private Integer inputItemStock() {
        int itemStock = getIntInput("아이템의 재고를 입력하세요: ");

        try {
            if (itemStock < 0) {
                throw new IllegalArgumentException("재고는 0개 이상이어야 합니다.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return itemStock;
    }


}
