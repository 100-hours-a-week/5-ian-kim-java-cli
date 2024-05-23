package view;

import model.Order;
import model.OrderItem;
import controller.OrderController;

import java.util.List;

import static util.DrawBox.formatLine;

public class OrderView {
//    private final OrderController orderController;
//    public OrderView(OrderController orderController) {
//        this.orderController = orderController;
//    }
//
//    public void createOrder(int tableId) {
//        orderController.createOrder(tableId);
//        System.out.println("주문이 완료되었습니다.");
//    }
//
//
//    public void orderHistory(int tableId) {
//        int maxWidth = 64;
//        System.out.println();
//        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//        System.out.println("┃　　　　　　　　　　　　　　　　　　 　　　　　　　　　　 　　　┃");
//        System.out.println("┃　　　　　               [주문 내역]                  　　　 　 ┃");
//        System.out.println("┃　　　　　　　　　　　　　　　　　　　　　　　　　 　　　　　　 ┃");
//        if(orderController.getOrders().isEmpty()) {
//            System.out.println("┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃");
//            System.out.println(formatLine("해당 테이블의 주문 내역이 없습니다.",maxWidth));
//            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
//            return;
//        }
//
//        List<Order> orderList = orderController.getOrderHistory(tableId);
//        for (Order order : orderList) {
//            System.out.println("┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃");
//            System.out.println(formatLine("주문번호: " + order.getId(), maxWidth));
//            System.out.println("┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃");
//            System.out.println(formatLine("주문날짜: " + order.getOrderDateFormat(), maxWidth));
//            System.out.println("┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃");
//            System.out.println(formatLine("테이블 번호: " + order.getTable().getTableNumber(),maxWidth));
//            for (OrderItem orderItem : order.getOrderItems()) {
//                String itemLine= "메뉴: " + orderItem.getItem().getName() + " 수량: " + orderItem.getCount() + " 가격: " + orderItem.getTotalPrice();
//                System.out.println(formatLine(itemLine, maxWidth));
//            }
//            System.out.println("┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃");
//            System.out.println(formatLine("총 가격: " + order.calculateTotalPrice() + "원",maxWidth));
//            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
//
//        }
//    }
}
