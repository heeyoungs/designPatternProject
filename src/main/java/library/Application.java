package library;

import exception.InputException;
import library.action.Action;
import library.gui.Gui;
import library.login.Login;
import library.person.Owner;
import library.service.CheckBookList;
import library.service.customerservice.BorrowBookByCustomer;
import library.service.customerservice.ReturnBookByCustomer;
import library.service.ownerservice.AddBookByOwner;
import library.service.ownerservice.RemoveBookByOwner;
import library.service.ownerservice.UpdateBookByOwner;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws InputException {
        Scanner scanner = new Scanner(System.in);

        Owner owner = new Owner();
        Gui gui = new Gui();

        boolean run = true;

        while (run) {
            gui.startGui();
            int who;
            Action action;
            try {
                who = scanner.nextInt();
            }catch (Exception e){
                throw new InputException("숫자를 입력안함.");
            }
            int input;

            switch (who) {
                case 0: {
                    gui.case0Gui();
                    return;
                }
                case 1: {
                    Login login = new Login();
                    login.loginOwner(owner);
                    outter:
                    while (true) {
                        gui.case1Gui();
                        try {
                            input = scanner.nextInt();
                        }catch (Exception e){
                            throw new InputException("숫자를 입력안함.");
                        }
                        switch (input) {
                            case 1:
                                action = new AddBookByOwner();
                                action.actionBook();
                                break;
                            case 2:
                                action = new RemoveBookByOwner();
                                action.actionBook();
                                break;
                            case 3:
                                action = new UpdateBookByOwner();
                                action.actionBook();
                                break;
                            case 4:
                                action = new CheckBookList();
                                action.actionBook();
                                break;
                            case 0:
                                gui.input0();
                                break outter;
                            default:
                                gui.defaultGui();
                                break;
                        }
                    }
                    break;
                }
                case 2: {
                    outter:
                    while (true) {
                        gui.case2Gui();
                        try {
                            input = scanner.nextInt();
                        }catch (Exception e){
                            throw new InputException("숫자를 입력안함.");
                        }
                        switch (input) {
                            case 1:
                                action = new BorrowBookByCustomer();
                                action.actionBook();
                                break;
                            case 2:
                                action = new ReturnBookByCustomer();
                                action.actionBook();
                                break;
                            case 3:
                                action = new CheckBookList();
                                action.actionBook();
                                break;
                            case 0:
                                gui.input0();
                                break outter;
                            default:
                                gui.defaultGui();
                                break;
                        }
                    }
                    break;
                }
                default: {
                    gui.defaultGui();
                    break;
                }
            }
        }
    }
}

// 손님 데이터베이스 테이블을 만들고 그걸로 옵저버 패턴을 만들까..?
