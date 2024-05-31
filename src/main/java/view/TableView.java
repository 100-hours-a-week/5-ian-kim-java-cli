package view;

import controller.TableController;
import controller.request.TableNumberRequest;
import controller.response.TableStatusResponse;
import util.AsciiArt;
import util.DrawBox;

import java.util.List;

public class TableView {
    private TableController tableController;
    public TableView(TableController tableController) {
        this.tableController = tableController;
    }

    public void showTableStatus() {
        AsciiArt.mainLogo();
        List<TableStatusResponse> tableStatuses = tableController.getTableStatuses().getResult();
        System.out.println("--------------------------------------------------");
        for(TableStatusResponse tableStatus : tableStatuses) {
            String text = "테이블 번호: " + tableStatus.getTableNumber() + "\n상태: " + tableStatus.getStatus() + "\n총 금액: " + tableStatus.getTotal();
            DrawBox.drawBox(50, 5, text);
        }
        System.out.println("--------------------------------------------------");
    }

    public boolean validateTableNumber(TableNumberRequest request) {
        if(!tableController.isValidTableNumber(request.getTableNumber()).getResult()) {
            System.out.println("잘못된 테이블 번호입니다.");
            return false;
        }
        return true;
    }
}