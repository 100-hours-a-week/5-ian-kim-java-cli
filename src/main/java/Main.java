import service.TableService;

import java.util.Scanner;

import static util.DrawBox.drawBox;

public class Main {
    public static void main(String[] args) {
        TableService tableService = new TableService();
        Scanner sc = new Scanner(System.in);
        boolean start = true;       //프로그램을 시작한다.
        int table = 0;
        String tableWithOrder;
        while(start) {
            tableWithOrder = table == 0 ? "테이블 현황" : "주문 하기";
            String text = "1. 주문하기    2. 관리자 전용   3. 테이블 현황    4. 프로그램 종료";
            // ASCII 아트로 네모난 박스 출력
            drawBox(100, 5, text);
            System.out.print("메뉴를 선택하세요: ");
            int menu = sc.nextInt();

            switch (menu) {
                case 1:
                        tableService.showTableStatus();
                        System.out.print("테이블 번호를 선택하세요: ");
                        table = sc.nextInt();
                        tableService.settingTable(table);


                    break;
                case 2:
                    System.out.println("관리자 전용");
                    break;

                case 3:
                    tableService.showTableStatus();
                    break;


                case 4:
                    System.out.println("프로그램 종료");
                    start = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }

        }
    }
}
