package service;

import controller.request.MenuNumberWithStockRequest;
import model.Order;
import model.OrderItem;
import model.Table;
import thread.CookStatusManager;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final List<Order> orders;
    private final OrderItemService orderItemService;
    private final TableService tableService;
    private final List<OrderItem> orderQueue;
    private final Object lock;

//    public OrderService(OrderItemService orderItemService, TableService tableService) {
//        this.orders = new ArrayList<>();
//        this.orderItemService = orderItemService;
//        this.tableService = tableService;
//    }

    public OrderService(OrderItemService orderItemService, TableService tableService, List<OrderItem> orderQueue, Object lock) {
        this.orders = new ArrayList<>();
        this.orderItemService = orderItemService;
        this.tableService = tableService;
        this.orderQueue = orderQueue;
        this.lock = lock;
    }


    public void addOrder(int tableId, MenuNumberWithStockRequest request) {
        List<OrderItem> orderItems = orderItemService.addOrderItem(request);
        Table table = tableService.findTableById(tableId);
        Order order = new Order(orderItems, table);
        orders.add(order);
        table.addOrder(order);
        table.occupyTable();


        Thread orderThread = new Thread(() -> {
                synchronized (lock) {
                    try {
                        orderQueue.add(orderItems.get(0));
                    } catch (IndexOutOfBoundsException e) {

                    }
                    lock.notifyAll(); // Notify all waiting threads
                    System.out.println("새 주문이 추가되었습니다.");
                }
        });

        orderThread.start();

    }

    public List<Order> getTableOrderHistory(int tableId) {
        return tableService.findTableById(tableId).getOrders();
    }



    public List<Order> getOrders() {
        return orders;
    }
}


