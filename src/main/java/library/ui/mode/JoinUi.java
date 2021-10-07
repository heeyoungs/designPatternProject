package library.ui.mode;

import exception.NumException;
import database.query.InsertService;
import library.ui.EtcUi;

public class JoinUi implements UiRun {

    public void run() throws NumException {
        System.out.print("회원등록을 하시겠습니까? 등록은 1번 다른 숫자를 눌러주세요: ");
        isJoin();
    }

    private void writeUserInfo(){
        InsertService insertService = new InsertService();
        EtcUi etcUi = new EtcUi();

        System.out.print("전화번호를 입력해주세요(-를 포함해주세요): ");
        String phoneNum = etcUi.inputString();
        System.out.print("사용자 이름을 입력해주세요: ");
        String userName = etcUi.inputString();
        insertService.joinUser(phoneNum, userName);
    }

    private void isJoin() throws NumException {
        EtcUi etcUi = new EtcUi();
        int num = etcUi.inputNum();
        if (num == 1) {
            writeUserInfo();
        } else {
            etcUi.logOutUi();
        }
    }
}
