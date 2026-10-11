package Controller;

import View.AddBookView;
import View.CreateUserView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUserController {
    
    CreateUserView CUView = new CreateUserView();
    
    public CreateUserController(){
    
        giveFunctions();
    }
    
    private void giveFunctions(){
    
        CUView.btnCreateUser.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
            
                
            }
        });
        
        CUView.btnReturn.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
            
                CUView.setVisible(false);
                CUView.dispose();
                LibraryController LController = new LibraryController();
                //ClientTurnController CTController = new ClientTurnController(CTView, 2);                 
            }
        });    
    }
}
