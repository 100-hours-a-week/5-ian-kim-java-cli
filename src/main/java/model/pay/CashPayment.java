package model.pay;


import model.Order;
import exception.PayException;
import model.OrderItem;

import java.util.List;

public class CashPayment extends Payment {
    private String cashReceiptNumber;

    @Override
    public void pay() throws PayException { //명시적인 이유
        if(orders.isEmpty()) {
            throw new PayException("주문을 하고 결제 해주세요.");
        }
        int totalPrice =orders.stream().mapToInt(Order::calculateTotalPrice).sum();
        if (totalPrice <= 0) {
            throw new PayException("가격이 잘못되었습니다.");
        } else {
            System.out.println("현금이 정상적으로 지불되었습니다.");
        }
    }

    public CashPayment() {

    }

    @Override
    public String toString() {
        int totalPrice = 0;
        String msg = "[현금 결제 정보]";
        msg += "\n상점명 : " + shopName;
        msg += "\n=================================";
        for (Order order : orders) {
            for (OrderItem orderItem : order.getOrderItems()) {
                msg += String.format("%-20s %10d", "\n상품명 : " + orderItem.getItem().getName(), orderItem.getTotalPrice());
            }
            totalPrice += order.calculateTotalPrice();
        }
        msg += "\n=================================";
        msg += "\n총 결제금액 : " + totalPrice;
        msg += "\n현금영수증 번호 : " + cashReceiptNumber;
        return msg;
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
