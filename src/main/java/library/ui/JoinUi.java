package library.ui;

import library.service.InsertService;

import java.util.Scanner;

public class JoinUi {

    public void run(){
        Scanner scanner = new Scanner(System.in);
        EtcUi etcUi = new EtcUi();

        InsertService insertService = new InsertService();

        System.out.print("회원등록을 하시겠습니까? 등록은 1번 다른 숫자를 눌러주세요: ");
        int input = scanner.nextInt();
        switch (input){
            case 1:
                String phoneNum,userName;
                System.out.print("전화번호를 입력해주세요(-를 포함해주세요): ");
                phoneNum = scanner.next();
                System.out.print("사용자 이름을 입력해주세요: ");
                userName = scanner.next();
                insertService.joinUser(phoneNum,userName);
                break;
            default:
                etcUi.logOutUi();
                break;
        }
    }
}
