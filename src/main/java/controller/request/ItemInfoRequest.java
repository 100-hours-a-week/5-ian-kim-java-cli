package controller.request;

public class ItemInfoRequest {
    private int categoryNumber;
    private String itemName;
    private int itemPrice;
    private int itemStock;

    public ItemInfoRequest(int categoryNumber, String itemName, int itemPrice, int itemStock) {
        this.categoryNumber = categoryNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
    }

    public int getCategoryNumber() {
        return categoryNumber;
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
