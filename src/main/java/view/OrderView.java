package view;

import controller.request.MenuNumberWithStockRequest;
import model.Order;
import model.OrderItem;
import controller.OrderController;

import java.util.List;

import static util.DrawBox.formatLine;
import static util.InputHandler.getIntInput;
import static util.InputHandler.getStringInput;

public class OrderView {
    private final OrderController orderController;

    public OrderView(OrderController orderController) {
        this.orderController = orderController;
    }

    //
//    public void createOrder(int tableId) {
//        orderController.createOrder(tableId);
//        System.out.println("주문이 완료되었습니다.");
//    }
//
//
    public void orderHistory(int tableId) {
        int maxWidth = 64;
        System.out.println();
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃　　　　　　　　　　　　　　　　　　 　　　　　　　　　　 　　　┃");
        System.out.println("┃　　　　　               [주문 내역]                  　　　 　 ┃");
        System.out.println("┃　　　　　　　　　　　　　　　　　　　　　　　　　 　　　　　　 ┃");

        List<Order> orderList = orderController.getTableOrderList(tableId).getResult();
        if (orderList.isEmpty()) {
            System.out.println("┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃");
            System.out.println(formatLine("해당 테이블의 주문 내역이 없습니다.", maxWidth));
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            return;
        }

        for (Order order : orderList) {
            System.out.println("┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃");
            System.out.println(formatLine("주문번호: " + order.getId(), maxWidth));
            System.out.println("┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃");
            System.out.println(formatLine("주문날짜: " + order.getOrderDateFormat(), maxWidth));
            System.out.println("┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃");
            System.out.println(formatLine("테이블 번호: " + order.getTable().getTableNumber(), maxWidth));
            for (OrderItem orderItem : order.getOrderItems()) {
                String itemLine = "메뉴: " + orderItem.getItem().getName() + " 수량: " + orderItem.getCount() + " 가격: " + orderItem.getTotalPrice();
                System.out.println(formatLine(itemLine, maxWidth));
            }
            System.out.println("┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃");
            System.out.println(formatLine("총 가격: " + order.calculateTotalPrice() + "원", maxWidth));
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

        }
    }

    public void orderSelected(int tableId) {

        boolean startedOrder = true;
        while (startedOrder) {
            int menuNumber = getIntInput("메뉴를 선택하세요 : ");

            int count = getIntInput("수량을 입력하세요: ");
            MenuNumberWithStockRequest request = new MenuNumberWithStockRequest(menuNumber, count);
            orderController.postOrder(tableId, request);
            System.out.println("===================================");
            String answer = getStringInput("추가 주문을 하시겠습니까? (Y/N): ");
            if (answer.equals("N")) {
                startedOrder = false;
            }
        }
    }
}
