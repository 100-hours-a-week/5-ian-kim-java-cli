package view;

import controller.request.TableNumberRequest;
import thread.CookStatusManager;
import util.PasswordManager;

import static util.DrawBox.drawBox;
import static util.InputHandler.getIntInput;
import static util.InputHandler.getStringInput;

public class MainView {
    TableView tableView;
    MenuView menuView;
    OrderView orderView;
    PayView payView;
    EmployeeView employeeView;
    CookStatusManager cookStatusManager;
    public MainView(TableView tableView, OrderView orderView, MenuView menuView, PayView payView, EmployeeView employeeView, CookStatusManager cookStatusManager) {
        this.tableView = tableView;
        this.menuView = menuView;
        this.orderView = orderView;
        this.payView = payView;
        this.employeeView = employeeView;
        this.cookStatusManager = cookStatusManager;
    }

    public void mainStart() {
        boolean start = true;       //프로그램을 시작한다.

        while (start) {
            String text = "1. 주문하기    2. 관리자 전용   3. 테이블 현황    4. 프로그램 종료";
            // ASCII 아트로 네모난 박스 출력
            drawBox(100, 5, text);

            int menu = getIntInput("메뉴를 선택하세요: ");
            switch (menu) {
                case 1:
                    boolean customer = true;
                    while (customer) {
                        tableView.showTableStatus();
                        int table = getIntInput("테이블 번호를 선택하세요 ('0' 을 입력하면 뒤로가기) : ");
                        if(table==0) {
                            break;
                        }
                        TableNumberRequest tableNumberRequest = new TableNumberRequest(table);
                        if(!tableView.validateTableNumber(tableNumberRequest)) {
                            continue;
                        }
                        drawBox(100, 5, "1. 메뉴 선택       2. 주문 확인       3. 결제 하기       4. 뒤로 가기       5. 요리 현황");
                        int selectedCustomer = getIntInput("메뉴를 선택하세요 : ");
                        switch (selectedCustomer) {
                            case 1:
                                menuView.displayMenu();
                                orderView.orderSelected(tableNumberRequest.getTableNumber());
                                break;
                            case 2:
                                orderView.orderHistory(tableNumberRequest.getTableNumber());
                                break;
                            case 3:
                                payView.displayPayment(table);
                                break;
                            case 4:
                                customer = false;
                                break;
                            case 5:
                                cookStatusManager.showCookStatus();
                                // 사용자 입력 받기
                                System.out.println("아무 키나 누르면 요리 현황을 숨깁니다.");
                                getStringInput("");

                                // CookStatusManager의 hideCookStatus() 메서드 호출
                                cookStatusManager.hideCookStatus();
                                break;
                            default:
                                System.out.println("잘못된 입력입니다.");
                        }
                    }
                    break;

                case 2:
                    String password = getStringInput("비밀번호를 입력하세요 : ");
                    PasswordManager passwordManager = new PasswordManager();
                    if (!passwordManager.checkPassword(password)) {
                        System.out.println("비밀번호가 틀렸습니다.");
                    } else {
                        boolean admin = true;
                        while (admin) {
                            drawBox(120, 5, "1. 매출 확인     2. 메뉴 추가     3. 메뉴 삭제      4. 메뉴 수정      5. 직원 관리     6. 뒤로가기");
                            int adminMenu = getIntInput("번호를 선택하세요: ");

                            switch (adminMenu) {
                                case 1:
                                    System.out.println("매출 확인");
                                    break;
                                case 2:
                                    menuView.selectedMenuInfo();
                                    break;
                                case 3:
                                    menuView.displayDeleteMenu();           // 메뉴 삭제
                                    break;
                                case 4:
                                    menuView.displayUpdateMenu();
                                    break;
                                case 5:
                                    employeeView.manageEmployees();
                                    break;
                                case 6:
                                    admin = false;
                                    break;
                                default:
                                    System.out.println("잘못된 입력입니다.");
                            }
                        }
                    }
                    break;

                case 3:
                    tableView.showTableStatus();
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
