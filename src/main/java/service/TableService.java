package service;

import domain.Table;
import util.DrawBox;

import java.util.ArrayList;
import java.util.List;

public class TableService {
    List <Table> tables;

    public TableService() {
        tables = new ArrayList<>();
        for(int i =1;i<=6;i++) {
            tables.add(new Table(i));
        }
    }

    public void showTableStatus() {
        System.out.println("테이블 현황");
        for(Table table : tables) {
            String status = table.getIsOccupied() ? "사용중" : "비어\n있음";
            String text = "테이블 번호는: " + table.getTableNumber() + "\n테이블 상태는: " + status;
            DrawBox.drawBox(50, 3, text);
        }
    }

    public void settingTable(int tableNumber) {
        for(Table table : tables) {
            if(table.getTableNumber() == tableNumber) {
                if(table.getIsOccupied()) {
                    System.out.println("----이미 사용중인 테이블입니다.----");
                    return;
                }
                table.occupyTable();
            }
        }
    }


}
