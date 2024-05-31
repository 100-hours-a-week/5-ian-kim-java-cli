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


    public String getName() {
        return item.getName();
    }
    public int getTotalPrice() {
        return item.getPrice() * count;
    }

    public void decrementCount() {      //주문 항목의 수량을 감소시킨다.
        --count;
    }
}
