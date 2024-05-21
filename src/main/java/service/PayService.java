package service;

import domain.Order;
import domain.pay.CardPayment;
import domain.pay.CashPayment;
import domain.pay.Payment;
import exception.PayException;

import java.util.List;
import java.util.Scanner;


public class PayService {
    Scanner sc = new Scanner(System.in);

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
        System.out.println("결제 방법을 선택해주세요. (1. card/ 2. cash)");
        int paymentMethodNumber = Integer.parseInt(sc.nextLine());
        switch (paymentMethodNumber) {
            case 1:
                System.out.println("카드번호를 입력해주세요.");
                String cardNumber = sc.nextLine();
                System.out.println("카드 비밀번호를 입력해주세요.");
                String cardPassword = sc.nextLine();
                System.out.println("할부개월수를 입력해주세요.");
                int monthlyInstallment = Integer.parseInt(sc.nextLine());

                try {
                    payByCard(tableId, cardNumber, cardPassword, monthlyInstallment);
                    tableService.clearTable(tableId);
                } catch (PayException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                System.out.println("현금영수증 번호를 입력해주세요.");
                String cashReceiptNumber = sc.nextLine();
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
