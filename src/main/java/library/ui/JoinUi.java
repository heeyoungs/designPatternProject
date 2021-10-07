package library.ui;

import exception.InputException;
import library.service.InsertService;

public class JoinUi {

    public void run() throws InputException {
        System.out.print("회원등록을 하시겠습니까? 등록은 1번 다른 숫자를 눌러주세요: ");

        EtcUi etcUi = new EtcUi();
        int num = etcUi.inputNum();

        if (num == 1) {
            writeUserInfo();
        } else {
            etcUi.logOutUi();
        }
    }

    void writeUserInfo(){
        InsertService insertService = new InsertService();
        EtcUi etcUi = new EtcUi();

        System.out.print("전화번호를 입력해주세요(-를 포함해주세요): ");
        String phoneNum = etcUi.inputString();
        System.out.print("사용자 이름을 입력해주세요: ");
        String userName = etcUi.inputString();
        insertService.joinUser(phoneNum, userName);
    }
}
