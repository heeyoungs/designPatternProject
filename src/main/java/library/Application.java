package library;

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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Owner owner = new Owner();
        Gui gui = new Gui();

        boolean run = true;

        while (run) {
            gui.startGui();
            int who = scanner.nextInt();
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
                        input = scanner.nextInt();
                        switch (input) {
                            case 1:
                                AddBookByOwner addBookByOwner = new AddBookByOwner();
                                addBookByOwner.addBook();
                                break;
                            case 2:
                                RemoveBookByOwner removeBookByOwner = new RemoveBookByOwner();
                                removeBookByOwner.removeBook();
                                break;
                            case 3:
                                UpdateBookByOwner updateBookByOwner = new UpdateBookByOwner();
                                updateBookByOwner.updateEMP_OPDATE();
                                break;
                            case 4:
                                CheckBookList checkBookList = new CheckBookList();
                                checkBookList.checkBook();
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
                        input = scanner.nextInt();
                        switch (input) {
                            case 1:
                                BorrowBookByCustomer borrowBookByCustomer = new BorrowBookByCustomer();
                                borrowBookByCustomer.updateBook();
                                break;
                            case 2:
                                ReturnBookByCustomer returnBookByCustomer = new ReturnBookByCustomer();
                                returnBookByCustomer.updateBook();
                                break;
                            case 3:
                                CheckBookList checkBookList = new CheckBookList();
                                checkBookList.checkBook();
                                break;
                            case 0:
                                gui.input0();
                                break outter;
                            default:
                                gui.defaultGui();
                                break;
                        }
                        break;
                    }
                }
                default: {
                    gui.defaultGui();
                    break;
                }
            }
        }
    }
}
