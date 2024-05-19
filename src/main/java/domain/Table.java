package domain;

public class Table {
    private int tableNumber;
    private boolean isOccupied;     // 테이블이 사용중인지 여부


    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        this.isOccupied = false;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }
    public void occupyTable() {
        this.isOccupied= true;
    }

    public void freeTable() {
        this.isOccupied = false;
    }
}
