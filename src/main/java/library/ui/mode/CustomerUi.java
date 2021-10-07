package library.ui.mode;

import exception.NumException;
import library.login.CustomerLogin;
import database.query.SelectService;
import database.query.UpdateService;
import library.ui.EtcUi;

public class CustomerUi implements UiRun {

    EtcUi etcUi = new EtcUi();

    private enum CustomerSkill {BORROW, RETURN, CHECK_ALL, CHECK_BORROW, EXIT, DEFAULT}

    private void customerUi() {
        System.out.println("-------------------------");
        System.out.println("어떤 기능을 이용하실 건가요.");
        System.out.println("1.책 빌려가기");
        System.out.println("2.책 반납하기");
        System.out.println("3.책 목록 확인하기");
        System.out.println("4.대여목록 확인하기");
        System.out.println("0.손님 모드 종료하기");
        System.out.print("입력 : ");
    }

    public void run() throws NumException {

        CustomerLogin customerLogin = new CustomerLogin();
        String phoneNum = customerLogin.customerLogin();

        if (phoneNum.equals("-1")) {
            return;
        }

        while (true) {
            customerUi();
            int num = etcUi.inputNum();

            if (num == 1) {
                customerSwitch(CustomerSkill.BORROW, phoneNum);
            } else if (num == 2) {
                customerSwitch(CustomerSkill.RETURN, phoneNum);
            } else if (num == 3) {
                customerSwitch(CustomerSkill.CHECK_ALL, phoneNum);
            } else if (num == 4) {
                customerSwitch(CustomerSkill.CHECK_BORROW, phoneNum);
            } else if (num == 0) {
                customerSwitch(CustomerSkill.EXIT, phoneNum);
                return;
            } else {
                customerSwitch(CustomerSkill.DEFAULT, phoneNum);
            }
        }
    }

    private void customerSwitch(CustomerSkill customerSkill, String phoneNum) throws NumException {
        UpdateService updateService = new UpdateService();
        SelectService selectService = new SelectService();

        switch (customerSkill) {
            case BORROW -> updateService.borrowBook(phoneNum);
            case RETURN -> updateService.returnBook(phoneNum);
            case CHECK_ALL -> selectService.showBookList();
            case CHECK_BORROW -> selectService.showBorrowBookList(phoneNum);
            case EXIT -> etcUi.exitUi();
            case DEFAULT -> etcUi.exceptionUi();
        }
    }
}
