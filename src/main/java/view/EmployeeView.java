package view;

import controller.EmployeeController;
import controller.request.EmployeeInfoRequest;
import controller.request.EmployeeNameRequest;
import enums.EmployeeRole;
import model.Employee;

import java.util.List;

import static util.DrawBox.drawBox;
import static util.InputHandler.getIntInput;
import static util.InputHandler.getStringInput;

public class EmployeeView {
    private final EmployeeController employeeController;
    public EmployeeView(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public void manageEmployees() {
        boolean running = true;
        while (running) {
            drawBox(80, 5, "1. 직원 추가     2. 직원 삭제     3. 직원 정보 확인     4. 뒤로가기");
            int menu = getIntInput("메뉴를 선택하세요: ");
            switch (menu) {
                case 1:
                    String name = getStringInput("직원 이름을 입력하세요: ");
                    drawBox(60, 3, "1. MANAGER     2. EMPLOYEE     3. INTERN");
                    int roleChoice = getIntInput("직원의 역할을 선택하세요: ");
                    try {
                        EmployeeRole employeeRole = EmployeeRole.fromInt(roleChoice);
                        int hourlyRate = getIntInput("직원의 시급을 입력하세요: ");
                        if(hourlyRate < 0) throw new IllegalArgumentException("시간당 요금은 음수가 될 수 없습니다: " + hourlyRate);
                        EmployeeInfoRequest employeeInfoRequest = new EmployeeInfoRequest(name, employeeRole.getDisplayName(), hourlyRate);
                        employeeController.createEmployee(employeeInfoRequest);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    String deleteName = getStringInput("삭제할 직원의 이름을 입력하세요: ");
                    EmployeeNameRequest employeeNameRequest = new EmployeeNameRequest(deleteName);
                    employeeController.deleteEmployee(employeeNameRequest.getName());

                    break;
                case 3:
                    try {
                        List<Employee> employees = employeeController.getEmployeeList().getResult();
                        for (Employee employee : employees) {
                            System.out.println(employee.getName() + " " + employee.getRole() + " " + employee.getHourlyRate());
                        }
                    } catch (NullPointerException e) {
                        System.out.println("직원이 존재하지 않습니다.");
                    }
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
