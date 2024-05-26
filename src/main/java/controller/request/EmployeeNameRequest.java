package controller.request;

import enums.EmployeeRole;

public class EmployeeNameRequest {
    private String name;
    public EmployeeNameRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
