package domain.pay;

import domain.Order;
import domain.OrderItem;
import exception.PayException;

import java.util.List;

public class CardPayment extends Payment {
    private String cardNumber;
    private String cardPassword;
    private int monthlyInstallment;

    @Override
    public void pay() throws PayException {
        if(orders.isEmpty()) {
            throw new PayException("주문을 하고 결제 해주세요.");
        }
        for (Order order : orders) {
                if (getMonthlyInstallment() < 0 || order.calculateTotalPrice() <= 0) {
                    throw new PayException("가격이나 할부개월수가 잘못되었습니다.");
                } else {
                    System.out.println("신용카드가 정상적으로 지불되었습니다.");
                }

        }
    }

    public CardPayment() {

    }

    public CardPayment(String shopName, List<Order> orders,String cardNumber, String cardPassword,
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

