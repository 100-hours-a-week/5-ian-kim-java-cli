package controller;

import controller.response.Response;
import service.TableService;

import java.util.List;

public class TableController {
    private final TableService tableService;
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    public Response<List<String>> getTableStatuses() {
        List<String> tableStatus = tableService.getTableStatuses();
        return Response.success(tableService.getTableStatuses());
    }

    public Response<Boolean> isValidTableNumber(int tableNumber) {
            boolean isValid = tableService.isValidTableNumber(tableNumber);
            return Response.success(isValid);
    }

}
