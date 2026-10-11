package Main;

import Controller.LibraryController;
import DataBaseConnection.BDConnection;
import View.AddBookView;
import View.CreateUserView;
import View.LibraryView;
import View.PrestamoView;

public class Main {

    public static void main(String[] args) {
        
        new BDConnection();
        //new AddBook();
        //new CreateUser();
        //new Prestamo();
        //new LibraryView();
        new LibraryController();
    }
}
