package model;

import enums.EmployeeRole;

public class Employee extends Person {

    String employeeRole;    //직급
    int hourlyRate;     //시급

    public Employee(String name, String employeeRole, int hourlyRate) {
        super(name);
        this.employeeRole = employeeRole;
        this.hourlyRate = hourlyRate;
    }

    public String getRole() {
        return employeeRole;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public int calculatePay(int hoursWorked) {
        return hoursWorked * hourlyRate;
    }
}


