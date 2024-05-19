package util;

public class PasswordManager {
    private String password = "1234";

    public PasswordManager() {

    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}
