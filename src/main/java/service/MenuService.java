package service;

import domain.Category;
import domain.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuService {
    Scanner sc = new Scanner(System.in);

    Category c100 = new Category("튀김류", 100);
    Category c200 = new Category("탕류", 200);
    Category c300 = new Category("주류", 300);
    Category c400 = new Category("음료", 400);
    Category c500 = new Category("사이드메뉴", 500);

    List<Category> categories = new ArrayList<>();
    List<Item> items = new ArrayList<>();

    public MenuService() {
        categories.add(c100);
        categories.add(c200);
        categories.add(c300);
        categories.add(c400);
        categories.add(c500);

        // 카테고리에 항목 추가
        c100.addItem(new Item("치킨 너겟", 10000)); // 튀김류
        c100.addItem(new Item("감자 튀김", 8000)); // 튀김류
        c200.addItem(new Item("된장찌개", 12000)); // 탕류류
        c200.addItem(new Item("김치찌개", 10000)); // 탕류류
        c200.addItem(new Item("부대찌개", 15000)); // 탕류류
        c300.addItem(new Item("맥주", 5000)); // 주류
        c300.addItem(new Item("소주", 4000)); // 주류
        c300.addItem(new Item("위스키", 10000)); // 주류
        c300.addItem(new Item("와인", 15000)); // 주류
        c400.addItem(new Item("콜라", 2000)); // 음료
        c400.addItem(new Item("오렌지 주스", 3000)); // 음료
        c400.addItem(new Item("레몬에이드", 3000)); // 음료
        c400.addItem(new Item("아메리카노", 4000)); // 음료
        c500.addItem(new Item("감자 튀김", 5000)); // 사이드메뉴
        c500.addItem(new Item("치즈스틱", 6000)); // 사이드메뉴
        c500.addItem(new Item("감자샐러드", 7000)); // 사이드메뉴
        c500.addItem(new Item("계란말이", 6000)); // 사이드메뉴

    }


    public List<Category> getCategories() {
        return categories;
    }

    public void displayMenu() {
        String asciiArt = """
                                
                                
                .___  ___.  _______ .__   __.  __    __ \s
                |   \\/   | |   ____||  \\ |  | |  |  |  |\s
                |  \\  /  | |  |__   |   \\|  | |  |  |  |\s
                |  |\\/|  | |   __|  |  . `  | |  |  |  |\s
                |  |  |  | |  |____ |  |\\   | |  `--'  |\s
                |__|  |__| |_______||__| \\__|  \\______/ \s
                                
                                
                """;
        System.out.println(asciiArt);
        for (Category category : categories) {
            System.out.println(category.getCategoryName());
            System.out.println("=================================================");
            for (Item item : category.getItems()) {
                int padding = 20 - item.getName().length();
                System.out.printf("%-10d %-" + padding + "s %5d원\n", item.getId(), item.getName(), item.getPrice());
            }
            System.out.println("================================================\n\n");
        }
    }

    public void itemRegister() {
        Optional<Category> findCategory = selectCategory();
        if (findCategory.isPresent()) {
            Category selectedCategory = findCategory.get();
            String itemName = inputItemName();
            if (itemName == null) return;
            Integer itemPrice = inputItemPrice();
            if (itemPrice == null) return;

            Item item = new Item(itemName, itemPrice);
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
        System.out.print("카테고리의 ID를 선택하세요 : ");
        int categorySelect = Integer.parseInt(sc.nextLine());
        return categories.stream().filter(category -> category.getIdCounter() / 100 == categorySelect).findFirst();
    }

    public void itemDelete() {
        displayMenu();
        System.out.println("삭제할 아이템의 ID를 입력하세요 : ");
        int itemId = Integer.parseInt(sc.nextLine());
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
        System.out.println("수정할 아이템의 ID를 입력하세요 : ");
        int itemId = Integer.parseInt(sc.nextLine());
        Optional<Item> findItem = findItemById(itemId);

        if (findItem.isPresent()) {
            Item item = findItem.get();
            String itemName = inputItemName();
            if (itemName == null) return;
            Integer itemPrice = inputItemPrice();
            if (itemPrice == null) return;
            item.setName(itemName);
            item.setPrice(itemPrice);

            System.out.println("아이템이 성공적으로 수정되었습니다.");
        } else {
            System.out.println("===========  해당하는 아이템이 없습니다.  ===========");
        }
    }

    private String inputItemName() {
        System.out.print("아이템의 이름을 입력하세요: ");
        String itemName = sc.nextLine();
        if (itemName.length() > 20) {
            System.out.println("아이템의 이름은 20자 이하여야 합니다.");
            return null;
        }
        return itemName;
    }

    private Integer inputItemPrice() {          //
        System.out.print("아이템의 가격을 입력하세요: ");
        try {
            int itemPrice = Integer.parseInt(sc.nextLine());
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


    private Optional<Item> findItemById(int itemId) {
        return categories.stream()
                .flatMap(category -> category.getItems().stream())
                .filter(item -> item.getId() == itemId)
                .findFirst();
    }
}
