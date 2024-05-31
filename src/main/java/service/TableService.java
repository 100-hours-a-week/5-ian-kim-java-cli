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
