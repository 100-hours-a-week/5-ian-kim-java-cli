package service;

import controller.request.PayByCardRequest;
import controller.request.PayByCashRequest;
import exception.PayException;
import model.Order;
import model.pay.CardPayment;
import model.pay.CashPayment;
import model.pay.Payment;

import java.util.List;


public class PayService {
    private final TableService tableService;

    public PayService(TableService tableService) {
        this.tableService = tableService;
    }

    public String payByCard(PayByCardRequest request) throws PayException {
        List<Order> orders = tableService.getOrderHistory(request.getTableId());
        CardPayment cardPayment = new CardPayment("ian's PUB", orders, request.getCardNumber(), request.getCardPassword(), request.getMonthlyInstallment());
        payProcess(cardPayment);

        return cardPayment.toString();
    }

    public String payByCash(PayByCashRequest request) throws PayException {
        List<Order> orders = tableService.getOrderHistory(request.getTableId());
        CashPayment cashPayment = new CashPayment("ian's PUB", request.getCashReceiptNumber(), orders);
        payProcess(cashPayment);

        return cashPayment.toString();
    }

    private void payProcess(Payment p) throws PayException {
        p.pay();
        System.out.println("결제가 완료되었습니다.");
    }


}
