package library.ui;

import exception.InputException;
import library.login.CustomerLogin;
import library.service.DeleteService;
import library.service.SelectService;
import library.service.UpdateService;

import java.util.Scanner;

public class CustomerUi {

    public void customerUi() {
        System.out.println("-------------------------");
        System.out.println("어떤 기능을 이용하실 건가요.");
        System.out.println("1.책 빌려가기");
        System.out.println("2.책 반납하기");
        System.out.println("3.책 목록 확인하기");
        System.out.println("4.대여목록 확인하기");
        System.out.println("5.회원 탈퇴하기");
        System.out.println("0.손님 모드 종료하기");
        System.out.print("입력 : ");
    }

    public void run() throws InputException {

        Scanner scanner = new Scanner(System.in);

        CustomerLogin customerLogin = new CustomerLogin();
        EtcUi etcUi = new EtcUi();

        String phoneNum = customerLogin.customerLogin();

        UpdateService updateService = new UpdateService();
        SelectService selectService = new SelectService();
        DeleteService deleteService = new DeleteService();

        if (phoneNum.equals("-1")){
            return;
        }

        int input;

        while (true) {
            customerUi();
            try {
                input = scanner.nextInt();
            }catch (Exception e){
                throw new InputException("숫자를 입력안함.");
            }

            switch (input) {
                case 1:
                    updateService.borrowBook(phoneNum);
                    break;
                case 2:
                    updateService.returnBook(phoneNum);
                    break;
                case 3:
                    selectService.showBookList();
                    break;
                case 4:
                    selectService.showBorrowBookList(phoneNum);
                    break;
                case 5:
                    deleteService.removeUser(phoneNum);
                    return;
                case 0:
                    etcUi.exitUi();
                    return;
                default:
                    etcUi.exceptionUi();
                    break;
            }
        }
    }
}
