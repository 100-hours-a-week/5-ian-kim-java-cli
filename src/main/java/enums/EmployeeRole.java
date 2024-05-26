package enums;

public enum EmployeeRole {
        MANAGER(1, "Manager"),
        EMPLOYEE(2, "Employee"),
        INTERN(3, "Intern");

        private int value;
        private String displayName;

        EmployeeRole(int value, String displayName) {
                this.value = value;
                this.displayName = displayName;
        }

        EmployeeRole () {

        }

        public int getValue() {
                return value;
        }

        public String getDisplayName() {
                return displayName;
        }

        public static EmployeeRole fromInt(int i) {
                for (EmployeeRole role : EmployeeRole.values()) {
                        if (role.getValue() == i) {
                                return role;
                        }
                }
                throw new IllegalArgumentException("No enum constant with value " + i);
        }

        @Override
        public String toString() {
                return displayName;
        }
}
