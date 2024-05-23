package controller;

import model.Employee;
import enums.EmployeeRole;

import java.util.ArrayList;
import java.util.List;

import static util.DrawBox.*;
import static util.InputHandler.*;

public class EmployeeController {
    List<Employee> employees= new ArrayList<>();

    public void manageEmployees() {
        boolean running = true;
        while (running) {
            drawBox(80,5,"1. 직원 추가     2. 직원 삭제     3. 직원 정보 확인     4. 뒤로가기");
            int menu = getIntInput("메뉴를 선택하세요: ");
            switch (menu) {
                case 1:
                    String name = getStringInput("직원 이름을 입력하세요: ");
                    drawBox(60,3,"1. MANAGER     2. EMPLOYEE     3. INTERN");
                    int roleChoice = getIntInput("직원의 역할을 선택하세요: ");
                    EmployeeRole employeeRole;
                    switch (roleChoice) {
                        case 1:
                            employeeRole = EmployeeRole.MANAGER;
                            break;
                        case 2:
                            employeeRole = EmployeeRole.EMPLOYEE;
                            break;
                        case 3:
                            employeeRole = EmployeeRole.INTERN;
                            break;
                        default:
                            System.out.println("잘못된 입력입니다.");
                            return;
                    }
                    int hourlyRate = getIntInput("직원의 시급을 입력하세요: ");
                    addEmployee(new Employee(name, employeeRole, hourlyRate));
                    System.out.println("직원이 성공적으로 추가되었습니다.");
                    break;
                case 2:
                    String deleteName = getStringInput("삭제할 직원의 이름을 입력하세요: ");
                    Employee employeeToDelete = null;
                    for (Employee employee : employees) {
                        if (employee.getName().equals(deleteName)) {
                            employeeToDelete = employee;
                            break;
                        }
                    }
                    if (employeeToDelete != null) {
                        employees.remove(employeeToDelete);
                        System.out.println("직원이 성공적으로 삭제되었습니다.");
                    } else {
                        System.out.println("해당 이름의 직원이 존재하지 않습니다.");
                    }
                    break;
                case 3:
                    if(employees.isEmpty()) {
                        System.out.println("직원이 존재하지 않습니다.");
                        break;
                    }
                    for (Employee employee : employees) {
                        System.out.println(employee.getName() + " " + employee.getRole() + " " + employee.getHourlyRate());
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
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
}