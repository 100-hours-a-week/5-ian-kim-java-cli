package model;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private int tableNumber;        //Id 역할을 함
    private boolean isOccupied;     // 테이블이 사용중인지 여부
    private List<Order> orders;     // 주문 목록


    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        this.isOccupied = false;
        this.orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }
    public void occupyTable() {
        this.isOccupied= true;
    }

    public void freeTable() {
        this.isOccupied = false;
    }

}
