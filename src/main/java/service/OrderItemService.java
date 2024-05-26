package service;

import controller.MenuController;
import controller.request.MenuNumberWithStockRequest;
import exception.OutOfStockException;
import model.Item;
import model.OrderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static util.InputHandler.getIntInput;
import static util.InputHandler.getStringInput;

public class OrderItemService {
    private MenuService menuService;

    private List<OrderItem> orderItems;

    public OrderItemService(MenuService menuService) {
        orderItems = new ArrayList<>();
        this.menuService = menuService;
    }
//
    public List<OrderItem> createOrderItem(MenuNumberWithStockRequest request) {      //주문 항목을 생성하고 반환한다.( 이걸 메서드를 두가지로 나눠서 하는게 나을까)
        orderItems = new ArrayList<>();         //이 코드를 넣지 않으면 리스트를 초기화하지 않기 때문에 계속해서 추가된다.
            Optional<Item> item = menuService.findItemById(request.getMenuNumber());
            if(item.isPresent()) {
                try {
                    OrderItem orderItem = new OrderItem(item.get(), request.getStock());
                    item.get().removeStock(request.getStock());
                    orderItems.add(orderItem);
                }catch (OutOfStockException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("해당하는 메뉴가 없습니다.");
            };

        return orderItems;
    }

}
