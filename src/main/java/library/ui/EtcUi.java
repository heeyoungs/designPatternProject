package library.ui;

import exception.DateException;
import exception.NumException;

import java.sql.Date;
import java.util.Scanner;

public class EtcUi {
    private final Scanner scanner = new Scanner(System.in);

    public void startUi() {
        System.out.println("안녕하세요. 자바 도서관에 오신 것을 환영합니다.");
        System.out.println("관리자는 1번, 손님은 2번, 등록은 3번 종료하시려면 0번을 눌러주세요.");
    }

    public void exitUi() {
        System.out.println("감사합니다. 다음에 또 이용해주세요!");
    }

    public void logOutUi() {
        System.out.println("초기화면으로 돌아갑니다.");
    }

    public void exceptionUi() {
        System.out.println("표기된 숫자 중에 하나를 눌러주세요!");
    }

    public int inputNum() throws NumException {
        int input;
        try {
            input = scanner.nextInt();
            return input;
        } catch (Exception e) {
            throw new NumException("숫자를 입력해주세요.");
        }
    }

    public Date inputDate() throws DateException {
        Date input;
        try {
            input = Date.valueOf(scanner.next());
            return input;
        } catch (Exception e) {
            throw new DateException("날짜를 입력해주세요.");
        }
    }

    public String inputString() {
        String input;
        input = scanner.next();
        return input;
    }
}
