package view;

import controller.PayController;
import controller.request.PayByCardRequest;
import controller.request.PayByCashRequest;
import controller.request.TableNumberRequest;
import controller.response.PayByCardResponse;
import controller.response.Response;
import exception.PayException;
import util.DrawBox;

import static util.InputHandler.getIntInput;
import static util.InputHandler.getStringInput;

public class PayView {
    private final PayController payController;

    public PayView(PayController payController) {
        this.payController = payController;
    }


    public void displayPayment(int tableId) {
        int paymentMethodNumber = getIntInput("결제 방법을 선택해주세요. (1. 카드 / 2. 현금) : ");
        switch (paymentMethodNumber) {
            case 1:
                String cardNumber = getStringInput("카드번호를 입력해주세요 ");
                String cardPassword = getStringInput("카드 비밀번호를 입력해주세요 ");
                int monthlyInstallment = getIntInput("할부개월수를 입력해주세요 : ");

                PayByCardRequest cardRequest = new PayByCardRequest(tableId, cardNumber, cardPassword, monthlyInstallment);
                Response<String> cardResponse = payController.processCardPayment(cardRequest);
                if (cardResponse.getResultCode()) {
                    String cardReceipt = cardResponse.getResult();
                    int cardHeight = cardReceipt.split("\n").length+10;
                    DrawBox.drawBox(80, cardHeight, cardReceipt);
                } else {
                    System.out.println("결제에 실패하였습니다.");
                }
                break;
            case 2:
                String cashReceiptNumber = getStringInput("현금영수증 번호를 입력해주세요 : ");
                PayByCashRequest cashRequest = new PayByCashRequest(tableId, cashReceiptNumber);
                Response<String> cashResponse = payController.processCashPayment(cashRequest);
                if(cashResponse.getResultCode()) {
                    String cashReceipt = cashResponse.getResult();
                    int cashHeight = cashReceipt.split("\n").length+10;
                    DrawBox.drawBox(80, cashHeight, cashReceipt);
                } else {
                    System.out.println("결제에 실패하였습니다.");
                }
                break;
            default:
                System.out.println("결제 방법을 잘못 입력하셨습니다.");
        }
    }
}
