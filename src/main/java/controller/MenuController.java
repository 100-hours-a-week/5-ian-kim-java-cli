package controller;

import controller.request.ItemInfoRequest;
import controller.request.UpdateItemInfoRequest;
import controller.response.DisplayMenuResponse;
import controller.response.Response;
import model.Category;
import model.Item;
import service.MenuService;

import java.util.List;
import java.util.stream.Collectors;

public class MenuController {
    private final MenuService menuService;
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

//
    public Response<List<Category>> getCategories() {
        System.out.printf("%-20s %-20s%n", "카테고리 ID", "카테고리 이름");
        System.out.println("----------------------------------------------------");
        return Response.success(menuService.getCategories());
    }


    public Response<Void> createMenu(ItemInfoRequest itemInfoRequest) {
        menuService.addItem(itemInfoRequest);
        return Response.success(null);
    }


    public Response<Void> deleteMenu(int itemId) {
        menuService.removeItem(itemId);
        return Response.success(null);
    }

    public Response<Void> updateMenu(UpdateItemInfoRequest itemInfoRequest) {
        menuService.updateItem(itemInfoRequest);
        return Response.success(null);
    }


    public Response<DisplayMenuResponse> getCategoriesWithMenu() {
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
