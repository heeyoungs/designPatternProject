package library.ui;

import exception.DateException;
import exception.NumException;

public class LibraryUi implements UiRun {

    public void run() throws NumException, DateException {

        UiRun uiRun;
        EtcUi etcUi = new EtcUi();

        while (true) {
            etcUi.startUi();
            int who = etcUi.inputNum();

            switch (who) {
                case 0 -> {
                    etcUi.exitUi();
                    return;
                }
                case 1 -> {
                    uiRun = new OwnerUi();
                    uiRun.run();
                }
                case 2 -> {
                    uiRun = new CustomerUi();
                    uiRun.run();
                }
                case 3 -> {
                    uiRun = new JoinUi();
                    uiRun.run();
                }
                default -> etcUi.exceptionUi();
            }
        }
    }
}
