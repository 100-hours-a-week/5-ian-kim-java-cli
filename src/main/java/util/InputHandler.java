package util;

import java.util.Scanner;

public class InputHandler {
        private static Scanner sc = new Scanner(System.in);

        public static String getStringInput(String prompt) {
            System.out.print(prompt);
            return sc.nextLine();
        }

        public static int getIntInput(String prompt) {
            System.out.print(prompt);
            int input;
            while (true) {
                try {
                    input = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("숫자를 입력해주세요 : ");
                }
            }
            return input;
        }
    
}
