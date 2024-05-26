package controller;

import controller.request.MenuNumberWithStockRequest;
import controller.response.Response;
import model.Order;
import model.OrderItem;
import model.Table;
import service.OrderService;

import javax.swing.text.html.parser.Entity;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

public class OrderController {
    OrderService orderService;
//
////
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
//
    public Response<Void> postOrder(int tableId, MenuNumberWithStockRequest request) {
        orderService.createOrder(tableId, request);
        return Response.success();
    }

    public Response<List<Order>> getTableOrderList(int tableId) {
        return Response.success(orderService.getTableOrderHistory(tableId));
    }

}

