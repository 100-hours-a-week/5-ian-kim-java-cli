package model.pay;

import model.Order;
import exception.PayException;
import model.OrderItem;

import java.util.List;

public class CardPayment extends Payment {
    private String cardNumber;
    private String cardPassword;
    private int monthlyInstallment;

    @Override
    public void pay() throws PayException {
        if (orders.isEmpty()) {
            throw new PayException("주문을 하고 결제 해주세요.");
        }
        int totalPrice = orders.stream().mapToInt(Order::calculateTotalPrice).sum();
        if (getMonthlyInstallment() < 0 || totalPrice <= 0) {
            throw new PayException("가격이나 할부개월수가 잘못되었습니다.");
        } else {
            System.out.println("신용카드가 정상적으로 지불되었습니다.");
        }

    }

    @Override
    public String toString() {
        int totalPrice = 0;
        String msg = "[신용카드 결제 정보]";
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
        msg += "\n신용카드번호 : " + cardNumber;
        msg += "\n할부개월 : " + monthlyInstallment;
        return msg;
    }

    public CardPayment(String shopName, List<Order> orders, String cardNumber, String cardPassword,
                       int monthlyInstallment) {
        super(shopName, orders);
        this.cardNumber = cardNumber;
        this.cardPassword = cardPassword;
        this.monthlyInstallment = monthlyInstallment;
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

