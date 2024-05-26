package service;

import controller.request.EmployeeInfoRequest;
import model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private List<Employee> employees;
    public EmployeeService() {
        employees = new ArrayList<>();
    }

    public void addEmployee(EmployeeInfoRequest employeeInfoRequest) {
        try {
            Employee employee = new Employee(employeeInfoRequest.getName(), employeeInfoRequest.getEmployeeRole(), employeeInfoRequest.getHourlyRate());
            employees.add(employee);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeEmployee(String name) {

        try {
            Employee employee = findEmployeeByName(name).orElseThrow(()-> new IllegalArgumentException("해당 이름의 직원이 존재하지 않습니다."));
            employees.remove(employee);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public Optional<Employee> findEmployeeByName(String name) {
        return employees.stream().filter(employee -> employee.getName().equals(name)).findFirst();
    }

    public List<Employee> getAllEmployees() {
        if(employees.isEmpty()) {
            throw new NullPointerException("직원이 존재하지 않습니다.");
        }
        return employees;
    }

}
