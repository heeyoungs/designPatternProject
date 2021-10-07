package library.ui.mode;

import exception.DateException;
import exception.NumException;
import library.login.OwnerLogin;
import database.query.DeleteService;
import database.query.InsertService;
import database.query.SelectService;
import database.query.UpdateService;
import library.ui.EtcUi;


public class OwnerUi implements UiRun {

    EtcUi etcUi = new EtcUi();

    private enum OwnerSkill {ADD, REMOVE, UPDATE, CHECK_ALL, CHECK_USER, CHECK_BORROW, EXIT, DEFAULT}

    private void ownerUi() {
        System.out.println("-------------------------");
        System.out.println("어떤 기능을 이용하실 건가요.");
        System.out.println("1.책 추가하기");
        System.out.println("2.책 제거하기");
        System.out.println("3.책 정보 갱신하기");
        System.out.println("4.책 목록 확인하기");
        System.out.println("5.사용자 조회하기");
        System.out.println("6.사용자 대출 현황 조회하기");
        System.out.println("0.관리자 모드 종료하기");
        System.out.print("입력 : ");
    }

    public void run() throws NumException, DateException {

        OwnerLogin login = new OwnerLogin();
        login.ownerLogin();

        while (true) {
            ownerUi();
            int input = etcUi.inputNum();
            if (input == 1) {
                ownerSwitch(OwnerSkill.ADD);
            } else if (input == 2) {
                ownerSwitch(OwnerSkill.REMOVE);
            } else if (input == 3) {
                ownerSwitch(OwnerSkill.UPDATE);
            } else if (input == 4) {
                ownerSwitch(OwnerSkill.CHECK_ALL);
            } else if (input == 5) {
                ownerSwitch(OwnerSkill.CHECK_USER);
            } else if (input == 6) {
                ownerSwitch(OwnerSkill.CHECK_BORROW);
            } else if (input == 0) {
                ownerSwitch(OwnerSkill.EXIT);
                return;
            } else {
                ownerSwitch(OwnerSkill.DEFAULT);
            }
        }
    }

    private void ownerSwitch(OwnerSkill ownerSkill) throws NumException, DateException {

        DeleteService deleteService = new DeleteService();
        InsertService insertService = new InsertService();
        SelectService selectService = new SelectService();
        UpdateService updateService = new UpdateService();

        switch (ownerSkill) {
            case ADD -> insertService.addBook();
            case REMOVE -> deleteService.removeBook();
            case UPDATE -> updateService.updateBookBirth();
            case CHECK_ALL -> selectService.showBookList();
            case CHECK_USER -> selectService.showUserList();
            case CHECK_BORROW -> selectService.showUserBorrowInfo();
            case EXIT -> etcUi.exitUi();
            case DEFAULT -> etcUi.exceptionUi();
        }
    }
}
