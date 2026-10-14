package Controller;

import Model.AddBookModel;
import Persistence.libroDAO;
import View.AddBookView;
import View.CreateUserView;
import View.LibraryView;
import View.PrestamoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LibraryController {
    
    LibraryView LView = new LibraryView();
    
    public LibraryController(){
    
        giveFunctions();
        addBookLibrary();
    }
    
    private void giveFunctions(){
    
        LView.btnAddBook.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
            
                LView.setVisible(false);
                LView.dispose();
                AddBookController ABController = new AddBookController();
                //ClientTurnController CTController = new ClientTurnController(CTView, 1);
            }
        });
        
        LView.btnPrestamo.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
            
                LView.setVisible(false);
                LView.dispose();
                PrestamoController PController = new PrestamoController();
                //ClientTurnController CTController = new ClientTurnController(CTView, 2);                 
            }
        });
        
        LView.btnCreateUser.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
            
                LView.setVisible(false);   
                LView.dispose();
                CreateUserController CUController = new CreateUserController();
                //ClientTurnController CTController = new ClientTurnController(CTView, 3);
            }
        });      
    }
    
    private void addBookLibrary(){
    
        libroDAO lDAO = new libroDAO();
        
        List<AddBookModel> books = lDAO.ListaLibro("");
        
        LView.tableModel.setRowCount(0);
        
        for(AddBookModel book : books){
        
            LView.tableModel.addRow(new Object[]{
            
                book.getbookCode(),
                book.getbookName(),
                book.getbookAuthor(),
                book.getbookYear()
            });
        }
    }
}
