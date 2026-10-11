package Controller;

import View.AddBookView;
import View.CreateUserView;
import View.LibraryView;
import View.PrestamoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryController {
    
    LibraryView LView = new LibraryView();
    
    public LibraryController(){
    
        giveFunctions();
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
}
