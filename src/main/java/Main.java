import static util.DrawBox.drawBox;

public class Main {
    public static void main(String[] args) {
        String text = "1. 주문 하기    2. 관리자 전용    3. 테이블 현황    4. 프로그램 종료";

        // ASCII 아트로 네모난 박스 출력
        drawBox(100, 5, text);
    }


}
