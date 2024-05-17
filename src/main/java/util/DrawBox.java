package util;

public class DrawBox {
    public static void drawBox(int width, int height, String text) {
        int textLength = text.codePointCount(0, text.length());

        if (width < 2) {
            System.out.println("너무 작은 크기의 박스입니다.");
            return;
        }

        if (width < textLength) {
            System.out.println("텍스트 길이가 너무 깁니다.");
            return;
        }

        String topHorizontalLine = "┏" + "━".repeat(width - 2) + "┓";
        String emptyLine = "┃" + " ".repeat(width - 2) + "┃";
        String bottomHorizontalLine = "┗" + "━".repeat(width - 2) + "┛";

        // 상단 가로선 출력
        System.out.println(topHorizontalLine);

        // 중간 부분 출력 (가로 맨 앞, 가운데 문자열, 가로 맨 뒤)
        for (int i = 0; i < height; i++) {
            if (i == (height / 2)) {
                System.out.println("┃" + centerString(text, width - 2) + "┃");
            } else {
                System.out.println(emptyLine);
            }
        }

        // 하단 가로선 출력
        System.out.println(bottomHorizontalLine);
    }

    // 문자열을 가운데 정렬하는 메서드
    public static String centerString(String text, int length) {
        // 문자열의 실제 길이 계산
        int textLength = text.codePoints().map(cp -> Character.UnicodeScript.of(cp) == Character.UnicodeScript.HANGUL ? 2 : 1).sum();

        if (textLength > length) {
            return text;
        }
        int padding = (length - textLength) / 2;
        return " ".repeat(padding) + text + " ".repeat(padding);
    }
}
