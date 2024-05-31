package controller;

import controller.response.Response;
import controller.response.TableStatusResponse;
import model.Order;
import model.Table;
import service.TableService;

import java.util.ArrayList;
import java.util.List;

public class TableController {
    private final TableService tableService;
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    public Response<List<TableStatusResponse>> getTableStatuses() {
        List<TableStatusResponse> tableStatusResponses = new ArrayList<>();
        List<Table> tables = tableService.getTables();
        for (Table table : tables) {
            String status = table.getIsOccupied() ? "사용중" : "비어있음";
            int total = table.getOrders().stream()
                    .mapToInt(Order::calculateTotalPrice)
                    .sum();
            TableStatusResponse tableStatusResponse = new TableStatusResponse(table.getTableNumber(), status, total);
            tableStatusResponses.add(tableStatusResponse);
        }
        return Response.success(tableStatusResponses);
    }

    public Response<Boolean> isValidTableNumber(int tableNumber) {
            boolean isValid = tableService.isValidTableNumber(tableNumber);
            return Response.success(isValid);
    }

}
