package service;

import domain.Order;
import domain.OrderItem;
import domain.Table;
import util.DrawBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static util.DrawBox.*;

public class OrderService {
    private final TableService tableService;
    private final OrderItemService orderItemService = new OrderItemService();

    private final List<Order> orders;

    public OrderService(TableService tableService) {
        orders = new ArrayList<>();
        this.tableService = tableService;
    }


    public void createOrder(int tableId) {
        List<OrderItem> orderItems = orderItemService.createOrderItem();
        Table table = tableService.findTableById(tableId);
        Order order = new Order(orderItems, table);
        orders.add(order);
        table.addOrder(order);
        table.occupyTable();
    }


    public void orderHistory(int tableId) {
        int maxWidth = 64;
        System.out.println();
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃　　　　　　　　　　　　　　　　　　 　　　　　　　　　　 　　　┃");
        System.out.println("┃　　　　　               [주문 내역]                  　　　 　 ┃");
        System.out.println("┃　　　　　　　　　　　　　　　　　　　　　　　　　 　　　　　　 ┃");
        for (Order order : orders) {
            if (order.getTable().getTableNumber() == tableId) {
                System.out.println("┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃");
                System.out.println(formatLine("주문번호: " + order.getId(), maxWidth));
                System.out.println("┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃");
                System.out.println(formatLine("주문날짜: " + order.getOrderDateFormat(), maxWidth));
                System.out.println("┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃");
                System.out.println(formatLine("테이블 번호: " + order.getTable().getTableNumber(),maxWidth));
                for (OrderItem orderItem : order.getOrderItems()) {
                    String itemLine= "메뉴: " + orderItem.getItem().getName() + " 수량: " + orderItem.getCount() + " 가격: " + orderItem.getTotalPrice();
                    System.out.println(formatLine(itemLine, maxWidth));
                }
                System.out.println("┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃");
                System.out.println(formatLine("총 가격: " + order.calculateTotalPrice() + "원",maxWidth));
                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            }
        }
    }
}

