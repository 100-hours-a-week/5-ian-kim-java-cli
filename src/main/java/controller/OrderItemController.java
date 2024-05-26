package controller;

import model.Item;
import model.OrderItem;
import exception.OutOfStockException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static util.InputHandler.*;

public class OrderItemController {
//    private MenuController menuController;
//
//    private List<OrderItem> orderItems;
//
//    public OrderItemController(MenuController menuController) {
//        orderItems = new ArrayList<>();
//        this.menuController = menuController;
//    }
//
//    public List<OrderItem> createOrderItem() {      //주문 항목을 생성하고 반환한다.( 이걸 메서드를 두가지로 나눠서 하는게 나을까)
//        orderItems = new ArrayList<>();         //이 코드를 넣지 않으면 리스트를 초기화하지 않기 때문에 계속해서 추가된다.
//        boolean startedOrder = true;
//        while(startedOrder) {
//            int menuNumber = getIntInput("메뉴를 선택하세요: ");
//            int count = getIntInput("수량을 입력하세요: ");
//            Optional<Item> item = menuController.findItemById(menuNumber);
//            if(item.isPresent()) {
//                try {
//                    OrderItem orderItem = new OrderItem(item.get(), count);
//                    item.get().removeStock(count);
//                    orderItems.add(orderItem);
//                }catch (OutOfStockException e) {
//                    System.out.println(e.getMessage());
//                }
//            } else {
//                System.out.println("해당하는 메뉴가 없습니다.");
//            };
//            System.out.println("========================================");
//            String answer = getStringInput("추가 주문을 하시겠습니까? (Y/N): ");
//            if(answer.equals("N")) {
//                startedOrder = false;
//            }
//        }
//        return orderItems;
//    }
}
