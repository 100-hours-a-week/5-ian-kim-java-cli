package service;

import domain.Order;
import domain.pay.CardPayment;
import domain.pay.CashPayment;
import domain.pay.Payment;
import exception.PayException;

import java.util.List;

import static util.InputHandler.*;


public class PayService {
    private final TableService tableService;

    public PayService(TableService tableService) {
        this.tableService = tableService;
    }

    private void payByCard(int tableId, String cardNumber, String cardPassword, int monthlyInstallment) throws PayException {
        List<Order> orders = tableService.getOrderHistory(tableId);
        CardPayment cardPayment = new CardPayment("ian's PUB", orders, cardNumber, cardPassword, monthlyInstallment);

        payProcess(cardPayment);

    }

    private void payByCash(int tableId, String cashReceiptNumber) throws PayException {
        List<Order> orders = tableService.getOrderHistory(tableId);
        CashPayment cashPayment = new CashPayment("ian's PUB", cashReceiptNumber, orders);
        payProcess(cashPayment);

    }

    public static void payProcess(Payment p) throws PayException {
        p.pay();
        System.out.println("결제가 완료되었습니다.");
    }

    public void displayPayment(int tableId) {
        int paymentMethodNumber = getIntInput("결제 방법을 선택해주세요. (1. 카드 / 2. 현금) : ");
        switch (paymentMethodNumber) {
            case 1:
                String cardNumber = getStringInput("카드번호를 입력해주세요 ");
                String cardPassword = getStringInput("카드 비밀번호를 입력해주세요 ");
                int monthlyInstallment = getIntInput("할부개월수를 입력해주세요 : ");

                try {
                    payByCard(tableId, cardNumber, cardPassword, monthlyInstallment);
                    tableService.clearTable(tableId);
                } catch (PayException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                String cashReceiptNumber = getStringInput("현금영수증 번호를 입력해주세요 : ");
                try {
                    payByCash(tableId, cashReceiptNumber);
                    tableService.clearTable(tableId);
                } catch (PayException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("결제 방법을 잘못 입력하셨습니다.");
        }
    }
}
