package Main;

import DataBaseConnection.BDConnection;
import View.AddBook;
import View.CreateUser;
import View.Prestamo;

public class Main {

    public static void main(String[] args) {
        
        new BDConnection();
        //new AddBook();
        //new CreateUser();
        new Prestamo();
    }
}
