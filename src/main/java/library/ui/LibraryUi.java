package library.ui;

import exception.DateException;
import exception.NumException;
import library.ui.mode.CustomerUi;
import library.ui.mode.JoinUi;
import library.ui.mode.OwnerUi;
import library.ui.mode.UiRun;

public class LibraryUi {

    EtcUi etcUi = new EtcUi();

    private enum Library {EXIT, OWNER, CUSTOMER, JOIN, DEFAULT}

    public void run() throws NumException, DateException {

        while (true) {
            etcUi.startUi();
            int who = etcUi.inputNum();

            if (who == 0) {
                librarySwitch(Library.EXIT);
                return;
            } else if (who == 1) {
                librarySwitch(Library.OWNER);
            } else if (who == 2) {
                librarySwitch(Library.CUSTOMER);
            } else if (who == 3) {
                librarySwitch(Library.JOIN);
            } else {
                librarySwitch(Library.DEFAULT);
            }

        }
    }

    public void librarySwitch(Library library) throws NumException, DateException {
        UiRun uiRun;

        switch (library) {
            case OWNER -> {
                uiRun = new OwnerUi();
                uiRun.run();
            }
            case CUSTOMER -> {
                uiRun = new CustomerUi();
                uiRun.run();
            }
            case JOIN -> {
                uiRun = new JoinUi();
                uiRun.run();
            }
            case EXIT -> etcUi.exitUi();
            case DEFAULT -> etcUi.exceptionUi();
        }
    }
}
