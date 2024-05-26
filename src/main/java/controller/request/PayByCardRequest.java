package controller.request;

public class PayByCardRequest {
    private int tableId;
    private String cardNumber;
    private String cardPassword;
    private int monthlyInstallment;

    public PayByCardRequest(int tableId, String cardNumber, String cardPassword, int monthlyInstallment) {
        this.tableId = tableId;
        this.cardNumber = cardNumber;
        this.cardPassword = cardPassword;
        this.monthlyInstallment = monthlyInstallment;
    }

    public int getTableId() {
        return tableId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardPassword() {
        return cardPassword;
    }

    public int getMonthlyInstallment() {
        return monthlyInstallment;
    }
}
