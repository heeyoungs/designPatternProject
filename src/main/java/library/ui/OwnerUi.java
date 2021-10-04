package library.ui;

import exception.InputException;
import library.login.OwnerLogin;
import library.service.DeleteService;
import library.service.InsertService;
import library.service.SelectService;
import library.service.UpdateService;

import java.util.Scanner;

public class OwnerUi {

    public void ownerUi() {
        System.out.println("-------------------------");
        System.out.println("어떤 기능을 이용하실 건가요.");
        System.out.println("1.책 추가하기");
        System.out.println("2.책 제거하기");
        System.out.println("3.책 정보 갱신하기");
        System.out.println("4.책 목록 확인하기");
        System.out.println("5.사용자 조회하기");
        System.out.println("0.관리자 모드 종료하기");
        System.out.print("입력 : ");
    }

    public void run() throws InputException {

        Scanner scanner = new Scanner(System.in);

        OwnerLogin login = new OwnerLogin();
        EtcUi etcUi = new EtcUi();

        login.ownerLogin();

        DeleteService deleteService = new DeleteService();
        InsertService insertService = new InsertService();
        SelectService selectService = new SelectService();
        UpdateService updateService = new UpdateService();

        int input;

        while (true) {
            ownerUi();
            try {
                input = scanner.nextInt();
            } catch (Exception e) {
                throw new InputException("숫자를 입력안함.");
            }
            switch (input) {
                case 1:
                    insertService.addBook();
                    break;
                case 2:
                    deleteService.removeBook();
                    break;
                case 3:
                    updateService.updateBookBirth();
                    break;
                case 4:
                    selectService.showBookList();
                    break;
                case 5:
                    selectService.showUserList();
                    break;
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
