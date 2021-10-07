package library;

import exception.InputException;
import library.ui.*;

public class Application {
    public static void main(String[] args) throws InputException {
        LibraryUi ui = new LibraryUi();
        ui.run();
    }
}