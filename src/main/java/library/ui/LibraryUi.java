package library.ui;

import exception.InputException;

import java.util.Scanner;

public class LibraryUi {

    public void run() throws InputException {

        CustomerUi customerUi = new CustomerUi();
        OwnerUi ownerUi = new OwnerUi();
        JoinUi joinUi = new JoinUi();
        EtcUi etcUi = new EtcUi();

        while (true) {
            etcUi.startUi();
            int who = etcUi.inputNum();

            switch (who) {
                case 0 -> {
                    etcUi.exitUi();
                    return;
                }
                case 1 -> ownerUi.run();
                case 2 -> customerUi.run();
                case 3 -> joinUi.run();
                default -> etcUi.exceptionUi();
            }
        }
    }
}
