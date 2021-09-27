package library.gui;

public class Gui {
    public void startGui() {
        System.out.println("안녕하세요. 자바 도서관에 오신 것을 환영합니다.");
        System.out.println("관리자는 1번, 손님은 2번, 종료하시려면 0번을 눌러주세요.");
    }

    public void case0Gui() {
        System.out.println("감사합니다. 다음에 또 이용해주세요!");
    }

    public void case1Gui() {
        System.out.println("-------------------------");
        System.out.println("어떤 기능을 이용하실 건가요.");
        System.out.println("1.책 추가하기");
        System.out.println("2.책 제거하기");
        System.out.println("3.책 정보 갱신하기");
        System.out.println("4.책 목록 확인하기");
        System.out.println("0.관리자 모드 종료하기");
        System.out.print("입력 : ");
    }

    public void case2Gui() {
        System.out.println("-------------------------");
        System.out.println("어떤 기능을 이용하실 건가요.");
        System.out.println("1.책 빌려가기");
        System.out.println("2.책 반납하기");
        System.out.println("3.책 목록 확인하기");
        System.out.println("0.손님 모드 종료하기");
        System.out.print("입력 : ");
    }

    public void input0() {
        System.out.println("초기화면으로 돌아갑니다.");
    }

    public void defaultGui() {
        System.out.println("표기된 숫자 중에 하나를 눌러주세요!");
    }
}
