package service;

import model.Order;
import model.Table;

import java.util.ArrayList;
import java.util.List;

public class TableService {

    List<Table> tables;

    public TableService() {
        tables = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            tables.add(new Table(i));
        }
    }

    public List<String> getTableStatuses() {
        List<String> tableStatus = new ArrayList<>();
        for (Table table : tables) {
            String status = table.getIsOccupied() ? "사용중" : "비어있음";
            int total = table.getOrders().stream()
                    .mapToInt(Order::calculateTotalPrice)
                    .sum();
            String text = "테이블 번호는: " + table.getTableNumber() + "\n테이블 상태는: " + status + "\n현재 금액: " + total;
            tableStatus.add(text);
        }
        return tableStatus;
    }


    public boolean isValidTableNumber(int tableNumber) {
        return tables.stream().anyMatch(table -> table.getTableNumber() == tableNumber);
    }


    public List<Order> getOrderHistory(int tableId) {
        Table table = findTableById(tableId);
        return table.getOrders();
    }

    public void clearTable(int tableId) {
        Table table = findTableById(tableId);
        table.getOrders().clear();
        table.freeTable();
    }

    public Table findTableById(int tableId) {
        return tables.stream().filter(table -> table.getTableNumber() == tableId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Table not found with id: " + tableId));
    }

    public List<Table> getTables() {
        return tables;
    }
}
