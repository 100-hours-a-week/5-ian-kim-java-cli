package controller.request;

public class EmployeeInfoRequest {
    private String name;
    private String employeeRole;
    private int hourlyRate;

    public EmployeeInfoRequest(String name, String employeeRole, int hourlyRate) {
        this.name = name;
        this.employeeRole = employeeRole;
        this.hourlyRate = hourlyRate;
    }

    public String getName() {
        return name;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }
}
