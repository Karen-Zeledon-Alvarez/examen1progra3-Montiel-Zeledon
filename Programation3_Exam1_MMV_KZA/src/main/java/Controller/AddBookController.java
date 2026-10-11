package Controller;

import View.AddBookView;
import View.CreateUserView;
import View.LibraryView;
import View.PrestamoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookController {
    
    AddBookView ABView = new AddBookView();
    
    public AddBookController(){
    
        giveFunctions();
    }
    
    private void giveFunctions(){
    
        ABView.btnAddBook.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
            
                String bookName = ABView.txtTitulo.getText().trim();
                String bookCode = ABView.txtISBN.getText().trim();
                String booKAuthor = ABView.txtAutor.getText().trim();
                String bookYear = ABView.txtAnio.getText().trim();
                
                
            }
        });
        
        ABView.btnReturn.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
            
                ABView.setVisible(false);
                ABView.dispose();
                LibraryController LController = new LibraryController();
                //ClientTurnController CTController = new ClientTurnController(CTView, 2);                 
            }
        });    
    }
}
