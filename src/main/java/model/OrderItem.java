package model;

public class OrderItem {
    private Item item;
    private int count;

    public OrderItem(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }



    public int getTotalPrice() {
        return item.getPrice() * count;
    }
}
