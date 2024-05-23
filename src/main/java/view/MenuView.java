package view;

import model.Category;
import model.Item;
import controller.MenuController;
import util.AsciiArt;

import java.util.List;

public class MenuView {
    private final MenuController menuController;
    public MenuView(MenuController menuController) {
        this.menuController = menuController;
    }
    public void displayMenu() {
//        AsciiArt.menuLogo();
//        List<Category> categories = menuController.getCategories().getResult();
//        for (Category category : categories) {
//            System.out.println(category.getCategoryName());
//            System.out.println("=================================================");
//            for (Item item : category.getItems()) {
//                if(item.getStock()>0) {
//                    int padding = 20 - item.getName().length() + (countSpaces(item.getName()));
//                    System.out.printf("%-10d %-" + padding + "s %5d원\n", item.getId(), item.getName(), item.getPrice());
//                }
//            }
//            System.out.println("================================================\n\n");
//        }
        System.out.println(menuController.getCategories());
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
}
