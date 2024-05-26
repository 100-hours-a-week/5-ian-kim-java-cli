package controller.request;

public class UpdateItemInfoRequest {
    private int itemNumber;
    private String itemName;
    private int itemPrice;
    private int itemStock;

    public UpdateItemInfoRequest(int itemNumber, String itemName, int itemPrice, int itemStock) {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getItemStock() {
        return itemStock;
    }
}
