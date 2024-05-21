package service;

import domain.Category;
import domain.Item;
import util.AsciiArt;
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

    }

    public void defaultMenu() {
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


    public List<Category> getCategories() {
        return categories;
    }


    public void displayMenu() {
        AsciiArt.menuLogo();
        for (Category category : categories) {
            System.out.println(category.getCategoryName());
            System.out.println("=================================================");
            for (Item item : category.getItems()) {
                if(item.getStock()>0) {
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

    public void itemRegister() {
        Optional<Category> findCategory = selectCategory();
        if (findCategory.isPresent()) {
            Category selectedCategory = findCategory.get();
            String itemName = inputItemName();
            Integer itemPrice = inputItemPrice();
            Integer itemStock = inputItemStock();
            if (itemName == null || itemPrice == null || itemStock == null) return;

            Item item = new Item(itemName, itemPrice, itemStock);
            selectedCategory.addItem(item);

            System.out.println("아이템이 성공적으로 등록되었습니다.");
        } else {
            System.out.println("===========  해당하는 카테고리가 없습니다.  ===========");
        }
    }

    private Optional<Category> selectCategory() {
        System.out.printf("%-20s %-20s%n", "카테고리 ID", "카테고리 이름");
        System.out.println("----------------------------------------------------");
        for (Category category : categories) {
            System.out.printf("%-20d %-20s%n", category.getIdCounter() / 100, category.getCategoryName());
        }
        int categorySelect = getIntInput("카테고리의 ID를 선택하세요 : ");
        return categories.stream().filter(category -> category.getIdCounter() / 100 == categorySelect).findFirst();
    }

    public void itemDelete() {
        displayMenu();
        int itemId = getIntInput("삭제할 아이템의 ID를 입력하세요 : ");
        Optional<Item> findItem = findItemById(itemId);

        System.out.println(findItem.toString());
        if (findItem.isPresent()) {
            Item item = findItem.get();
            item.getCategory().removeItem(item);
            System.out.println("아이템이 성공적으로 삭제되었습니다.");
        } else {
            System.out.println("===========  해당하는 아이템이 없습니다.  ===========");

        }
    }


    public void itemUpdate() {
        displayMenu();
        int itemId = getIntInput("수정할 아이템의 ID를 입력하세요 : ");
        Optional<Item> findItem = findItemById(itemId);

        if (findItem.isPresent()) {
            Item item = findItem.get();
            String itemName = inputItemName();
            Integer itemPrice = inputItemPrice();
            Integer itemStock = inputItemStock();
            if (itemName == null || itemPrice == null || itemStock == null) return;

            item.setName(itemName);
            item.setPrice(itemPrice);
            item.setStock(itemStock);

            System.out.println("아이템이 성공적으로 수정되었습니다.");
        } else {
            System.out.println("===========  해당하는 아이템이 없습니다.  ===========");
        }
    }

    private String inputItemName() {
        String itemName = getStringInput("아이템의 이름을 입력하세요: ");
        if (itemName.length() > 20) {
            System.out.println("아이템의 이름은 20자 이하여야 합니다.");
            return null;
        }
        return itemName;
    }

    private Integer inputItemPrice() {          //
        try {
            int itemPrice = getIntInput("아이템의 가격을 입력하세요: ");
            if (itemPrice < 0) {
                System.out.println("가격은 0원 이상이어야 합니다.");
                return null;
            }
            return itemPrice;
        } catch (NumberFormatException e) {
            System.out.println("가격은 숫자로 입력해주세요.");
            return null;
        }
    }

    private Integer inputItemStock() {
        try {
            int itemStock = getIntInput("아이템의 재고를 입력하세요: ");
            if (itemStock < 0) {
                System.out.println("재고는 0개 이상이어야 합니다.");
                return null;
            }
            return itemStock;
        } catch (NumberFormatException e) {
            System.out.println("재고는 숫자로 입력해주세요.");
            return null;
        }
    }


    public Optional<Item> findItemById(int itemId) {
        return categories.stream()
                .flatMap(category -> category.getItems().stream())
                .filter(item -> item.getId() == itemId)
                .findFirst();
    }
}
