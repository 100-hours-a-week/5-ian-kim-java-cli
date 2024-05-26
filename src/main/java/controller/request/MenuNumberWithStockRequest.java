package controller.request;

public class MenuNumberWithStockRequest {
    private int menuNumber;
    private int stock;

    public MenuNumberWithStockRequest(int menuNumber, int stock) {
        this.menuNumber = menuNumber;
        this.stock = stock;
    }

    public int getMenuNumber() {
        return menuNumber;
    }

    public int getStock() {
        return stock;
    }
}
