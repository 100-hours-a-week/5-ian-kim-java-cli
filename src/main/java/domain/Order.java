package domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private static int idCounter = 0;
    private int id;
    private List<OrderItem> orderItems;
    private Date orderDate;
    private Table table;

    public Order(List<OrderItem> orderItems, Table table) {
        this.id = ++idCounter;
        this.orderItems = orderItems;
        this.table = table;
        this.orderDate = new Date();
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    public String getOrderDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd a hh:mm:ss");
        return sdf.format(orderDate);
    }

    public int getId() {
        return id;
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public Table getTable() {
        return table;
    }
}
