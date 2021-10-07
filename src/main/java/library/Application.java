package library;

import exception.DateException;
import exception.NumException;
import library.ui.*;

public class Application {

    public static void main(String[] args) throws NumException, DateException {
        LibraryUi ui = new LibraryUi();
        ui.run();
    }
}