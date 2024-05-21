package service;

import domain.Item;
import domain.OrderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class OrderItemService {
    Scanner sc = new Scanner(System.in);
    private MenuService menuService = new MenuService();

    private List<OrderItem> orderItems;

    public OrderItemService() {
        orderItems = new ArrayList<>();
    }

    public List<OrderItem> createOrderItem() {      //주문 항목을 생성하고 반환한다.( 이걸 메서드를 두가지로 나눠서 하는게 나을까)
        orderItems = new ArrayList<>();         //이 코드를 넣지 않으면 리스트를 초기화하지 않기 때문에 계속해서 추가된다.
        menuService.displayMenu();
        boolean startedOrder = true;
        while(startedOrder) {
            System.out.print("메뉴를 선택하세요: ");
            int menuNumber = Integer.parseInt(sc.nextLine());
            System.out.print("수량을 입력하세요: ");
            int count = Integer.parseInt(sc.nextLine());
            Optional<Item> item = menuService.findItemById(menuNumber);
            if(item.isPresent()) {
                OrderItem orderItem = new OrderItem(item.get(), count);
                orderItems.add(orderItem);
            } else {
                System.out.println("해당하는 메뉴가 없습니다.");
            };
            System.out.println("========================================");
            System.out.print("추가 주문을 하시겠습니까? (Y/N): ");
            String answer = sc.nextLine();
            if(answer.equals("N")) {
                startedOrder = false;
            }
        }
        return orderItems;
    }
}