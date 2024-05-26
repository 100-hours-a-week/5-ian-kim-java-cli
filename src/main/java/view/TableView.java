package view;

import controller.TableController;
import controller.request.TableNumberRequest;
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
        List<String> tableStatuses = tableController.getTableStatuses().getResult();
        for(String text : tableStatuses) {
            DrawBox.drawBox(50, 5, text);
        }
    }

    public boolean validateTableNumber(TableNumberRequest request) {
        if(!tableController.isValidTableNumber(request.getTableNumber()).getResult()) {
            System.out.println("잘못된 테이블 번호입니다.");
            return false;
        }
        return true;
    }
}
