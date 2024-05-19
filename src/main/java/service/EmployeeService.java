package service;

import domain.Employee;
import domain.Role;
import util.DrawBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static util.DrawBox.*;

public class EmployeeService {
    List<Employee> employees= new ArrayList<>();

    public void manageEmployees() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            drawBox(80,5,"1. 직원 추가     2. 직원 삭제     3. 직원 정보 확인     4. 뒤로가기");
            System.out.print("메뉴를 선택하세요: ");
            int menu = sc.nextInt();
            switch (menu) {
                case 1:
                    System.out.print("직원 이름을 입력하세요: \n");
                    String name = sc.next();
                    System.out.println("직원의 역할을 선택하세요: \n");
                    drawBox(60,3,"1. MANAGER     2. EMPLOYEE     3. INTERN");
                    int roleChoice = sc.nextInt();
                    Role role;
                    switch (roleChoice) {
                        case 1:
                            role = Role.MANAGER;
                            break;
                        case 2:
                            role = Role.EMPLOYEE;
                            break;
                        case 3:
                            role = Role.INTERN;
                            break;
                        default:
                            System.out.println("잘못된 입력입니다.");
                            return;
                    }
                    System.out.print("직원의 시급을 입력하세요: ");
                    int hourlyRate = sc.nextInt();
                    addEmployee(new Employee(name, role, hourlyRate));
                    System.out.println("직원이 성공적으로 추가되었습니다.");
                    break;
                case 2:
                    System.out.print("삭제할 직원의 이름을 입력하세요: ");
                    String deleteName = sc.next();
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

    public List<Employee> getEmployees() {
        return employees;
    }


}