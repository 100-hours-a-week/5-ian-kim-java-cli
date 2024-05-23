package model.pay;


import model.Order;
import exception.PayException;

import java.util.List;

public class CashPayment extends Payment {
    private String cashReceiptNumber;

    @Override
    public void pay() throws PayException { //명시적인 이유
        if(orders.isEmpty()) {
            throw new PayException("주문을 하고 결제 해주세요.");
        }
        for (Order order : orders) {
            if (order.calculateTotalPrice() <= 0) {
                throw new PayException("가격이 잘못되었습니다.");
            } else {
                System.out.println("현금이 정상적으로 지불되었습니다.");
            }
        }
    }

    public CashPayment() {

    }

    public CashPayment(String shopName, String cashReceiptNumber, List<Order> orders) {
        super(shopName, orders);
        this.cashReceiptNumber = cashReceiptNumber;
    }

    public String getCashReceiptNumber() {
        return cashReceiptNumber;
    }

    public void setCashReceiptNumber(String cashReceiptNumber) {
        this.cashReceiptNumber = cashReceiptNumber;
    }
}
