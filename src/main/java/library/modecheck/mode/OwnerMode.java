package library.modecheck.mode;

import library.input.Input;
import exception.DateException;
import exception.NumException;
import library.login.OwnerLogin;
import database.query.DeleteService;
import database.query.InsertService;
import database.query.SelectService;
import database.query.UpdateService;
import library.modecheck.EtcUi;


public class OwnerMode implements Mode {

    EtcUi etcUi = EtcUi.getEtcUi();
    Input input = Input.getInput();

    private enum OwnerSkill {ADD, REMOVE, UPDATE, CHECK_ALL, CHECK_USER, CHECK_BORROW, DEFAULT}

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

        do {
            ownerUi();
            int command = input.inputNum();
            OwnerSkill ownerSkill;

            if (command == 1) {
                ownerSkill = OwnerSkill.ADD;
            } else if (command == 2) {
                ownerSkill = OwnerSkill.REMOVE;
            } else if (command == 3) {
                ownerSkill = OwnerSkill.UPDATE;
            } else if (command == 4) {
                ownerSkill = OwnerSkill.CHECK_ALL;
            } else if (command == 5) {
                ownerSkill = OwnerSkill.CHECK_USER;
            } else if (command == 6) {
                ownerSkill = OwnerSkill.CHECK_BORROW;
            } else if (command == 0) {
                etcUi.logOutUi();
                return;
            } else {
                ownerSkill = OwnerSkill.DEFAULT;
            }
            ownerSwitch(ownerSkill);
        }while(true);
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
            case DEFAULT -> etcUi.defaultUi();
        }
    }
}
