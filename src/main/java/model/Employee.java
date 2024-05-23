package model;

import enums.EmployeeRole;

public class Employee extends Person {

    EmployeeRole employeeRole;;
    int hourlyRate;     //시급

    public Employee(String name, EmployeeRole employeeRole, int hourlyRate) {
        super(name);
        this.employeeRole = employeeRole;
        this.hourlyRate = hourlyRate;
    }

    public EmployeeRole getRole() {
        return employeeRole;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public int calculatePay(int hoursWorked) {
        return hoursWorked * hourlyRate;
    }
}


