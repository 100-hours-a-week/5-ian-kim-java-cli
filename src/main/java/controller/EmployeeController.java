package controller;

import controller.request.EmployeeInfoRequest;
import controller.response.Response;
import model.Employee;
import service.EmployeeService;

import java.util.List;

public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    public Response<Void> createEmployee(EmployeeInfoRequest employeeInfoRequest) {
        employeeService.addEmployee(employeeInfoRequest);
        return Response.success(null);
    }

    public Response<Void> deleteEmployee(String name) {
        employeeService.removeEmployee(name);
        return Response.success(null);
    }

    public Response<List<Employee>> getEmployeeList() {
        return Response.success(employeeService.getAllEmployees());
    }
}