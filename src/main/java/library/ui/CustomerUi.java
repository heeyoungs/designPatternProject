package library.ui;

import exception.NumException;
import library.login.CustomerLogin;
import library.service.SelectService;
import library.service.UpdateService;

public class CustomerUi implements UiRun{

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
        if (phoneNum.equals("-1")){
            return;
        }

        EtcUi etcUi = new EtcUi();
        UpdateService updateService = new UpdateService();
        SelectService selectService = new SelectService();

        while (true) {
            customerUi();
            int num = etcUi.inputNum();

            switch (num) {
                case 1 -> updateService.borrowBook(phoneNum);
                case 2 -> updateService.returnBook(phoneNum);
                case 3 -> selectService.showBookList();
                case 4 -> selectService.showBorrowBookList(phoneNum);
                case 0 -> {
                    etcUi.exitUi();
                    return;
                }
                default -> etcUi.exceptionUi();
            }
        }
    }
}
