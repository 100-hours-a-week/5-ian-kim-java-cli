package controller.response;

public class TableStatusResponse {

    private String status;
    private int total;
    private int tableNumber;

    public TableStatusResponse(int tableNumber, String status, int total) {
        this.tableNumber = tableNumber;
        this.status = status;
        this.total = total;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }
}
