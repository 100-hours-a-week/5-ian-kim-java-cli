package controller.request;

public class PayByCashRequest {
    private int tableId;
    private String cashReceiptNumber;

    public PayByCashRequest(int tableId, String cashReceiptNumber) {
        this.tableId = tableId;
        this.cashReceiptNumber = cashReceiptNumber;
    }

    public int getTableId() {
        return tableId;
    }

    public String getCashReceiptNumber() {
        return cashReceiptNumber;
    }
}
