package controller;

import controller.request.MenuNumberWithStockRequest;
import controller.response.Response;
import model.Order;
import service.OrderService;

import java.util.List;

public class OrderController {
    OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    public Response<Void> postOrder(int tableId, MenuNumberWithStockRequest request) {
        orderService.addOrder(tableId, request);
        return Response.success();
    }

    public Response<List<Order>> getTableOrderList(int tableId) {
        return Response.success(orderService.getTableOrderHistory(tableId));
    }

}

