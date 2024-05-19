package domain;

public class Employee extends Person {

    Role role;;
    int hourlyRate;     //시급

    public Employee(String name, Role role, int hourlyRate) {
        super(name);
        this.role = role;
        this.hourlyRate = hourlyRate;
    }

    public Role getRole() {
        return role;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public int calculatePay(int hoursWorked) {
        return hoursWorked * hourlyRate;
    }
}


