import domain.Category;
import domain.Item;
import service.EmployeeService;
import service.MenuService;
import service.TableService;
import util.PasswordManager;

import java.util.Scanner;

import static util.DrawBox.drawBox;

public class Main {
    public static void main(String[] args) {
        TableService tableService = new TableService();
        EmployeeService employeeService = new EmployeeService();
        MenuService menuService = new MenuService();

        Scanner sc = new Scanner(System.in);
        boolean start = true;       //프로그램을 시작한다.
        int table = 0;
        String tableWithOrder;

        while (start) {
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
                    if(!tableService.settingTable(table)) {
                        break;
                    }
                    menuService.displayMenu();
                    break;
                case 2:
                    System.out.println("비밀번호를 입력하세요");
                    String password = sc.next();
                    PasswordManager passwordManager = new PasswordManager();
                    if (!passwordManager.checkPassword(password)) {
                        System.out.println("비밀번호가 틀렸습니다.");
                    } else {
                        drawBox(120, 5, "1. 매출 확인     2. 메뉴 추가     3. 메뉴 삭제      4. 메뉴 수정      5. 직원 관리     6. 뒤로가기");
                        System.out.print("번호를 선택하세요: ");
                        int adminMenu = sc.nextInt();
                        switch (adminMenu) {
                            case 1:
                                System.out.println("매출 확인");
                                break;
                            case 2:
                                menuService.itemRegister();
                                break;
                            case 3:
                                menuService.itemDelete();           // 메뉴 삭제
                                break;
                            case 4:
                                menuService.itemUpdate();
                                break;
                            case 5:
                                employeeService.manageEmployees();
                                break;
                            case 6:
                                break;
                            default:
                                System.out.println("잘못된 입력입니다.");
                        }
                    }
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
