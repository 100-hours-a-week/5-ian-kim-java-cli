package domain.pay;

import domain.Order;
import exception.PayException;

import java.util.List;

abstract public class Payment implements Payable {
    protected String shopName;
    protected List<Order> orders;

    public Payment() {

    }
    public Payment(String shopName, List<Order> orders) {
        super();
        this.shopName = shopName; // this 키워드는 인스턴스 자기 자신을 뜻한다
        this.orders=orders;
    }

    @Override
    public abstract void pay() throws PayException;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
