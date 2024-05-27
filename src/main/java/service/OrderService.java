package service;

import controller.request.MenuNumberWithStockRequest;
import model.Order;
import model.OrderItem;
import model.Table;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final List<Order> orders;
    private final OrderItemService orderItemService;
    private final TableService tableService;

    public OrderService( OrderItemService orderItemService, TableService tableService) {
        this.orders = new ArrayList<>();
        this.orderItemService = orderItemService;
        this.tableService = tableService;
    }


    public void addOrder(int tableId, MenuNumberWithStockRequest request) {
        List<OrderItem> orderItems = orderItemService.addOrderItem(request);
        Table table = tableService.findTableById(tableId);
        Order order = new Order(orderItems, table);
        orders.add(order);
        table.addOrder(order);
        table.occupyTable();
    }

        public List<Order> getTableOrderHistory(int tableId) {
        return tableService.findTableById(tableId).getOrders();
    }

    public List<Order> getOrders() {
        return orders;
    }

}
