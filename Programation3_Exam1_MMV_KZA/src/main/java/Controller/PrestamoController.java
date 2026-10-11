package Controller;

import View.CreateUserView;
import View.PrestamoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrestamoController {
    
    PrestamoView PView = new PrestamoView();
    
    public PrestamoController(){
    
        giveFunctions();
    }
    
    private void giveFunctions(){
    
        PView.btnAddBook.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
            
                
            }
        });
        
        PView.btnReturn.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
            
                PView.setVisible(false);
                PView.dispose();
                LibraryController LController = new LibraryController();
                //ClientTurnController CTController = new ClientTurnController(CTView, 2);                 
            }
        });    
    }
}
