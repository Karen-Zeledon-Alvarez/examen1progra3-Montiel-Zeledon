
package Controller;

import Model.AddBookModel;
import Model.BookManager;
import Persistence.libroDAO;
import Persistence.ejemplarDAO;
import View.AddBookView;
import View.BookCopiesView;
import View.CreateUserView;
import View.LibraryView;
import View.PrestamoView;

import java.util.List;
import javax.swing.JOptionPane;

public class LibraryController {

    private final LibraryView LView;
    private final libroDAO lDAO;
    private final ejemplarDAO eDAO;

    public LibraryController() {

        LView = new LibraryView();
        lDAO = new libroDAO();
        eDAO = new ejemplarDAO();

        giveFunctions();
        addBookLibrary("");
    }

    private void giveFunctions() {

        // Registrar libros
        LView.btnAddBook.addActionListener(e -> {
            new AddBookController();
        });

        // Administrar ejemplares
        LView.btnBookCopies.addActionListener(e -> abrirEjemplares());

        // Préstamos
        LView.btnPrestamo.addActionListener(e -> {
            new PrestamoController();
        });

        // Crear usuarios
        LView.btnCreateUser.addActionListener(e -> {
            new CreateUserController();
        });

        // Buscar libros
        LView.btnBuscar.addActionListener(e -> {
            addBookLibrary(LView.txtBuscar.getText().trim());
        });

        // Mostrar todos los libros
        LView.btnMostrarTodos.addActionListener(e -> {
            LView.txtBuscar.setText("");
            addBookLibrary("");
        });

        // Buscar al presionar Enter
        LView.txtBuscar.addActionListener(e -> {
            addBookLibrary(LView.txtBuscar.getText().trim());
        });
    }

    private void addBookLibrary(String texto) {

        List<AddBookModel> books = lDAO.ListaLibro(texto);

        LView.tableModel.setRowCount(0);

        for (AddBookModel book : books) {
            LView.tableModel.addRow(new Object[]{
                book.getbookCode(),
                book.getbookName(),
                book.getbookAuthor(),
                book.getbookYear()
            });
        }
    }

    
private void abrirEjemplares() {

    List<AddBookModel> libros = lDAO.ListaLibro("");

    if (libros == null || libros.isEmpty()) {
        JOptionPane.showMessageDialog(
            LView,
            "Primero debes registrar un libro."
        );
        return;
    }

    BookCopiesView copiesView = new BookCopiesView(libros);
    new BookCopiesController(copiesView);
}

}
