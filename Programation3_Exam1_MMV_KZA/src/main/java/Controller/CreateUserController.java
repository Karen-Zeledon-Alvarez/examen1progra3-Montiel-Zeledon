package Controller;

import Model.CreateUserModel;
import Persistence.usuarioDAO;
import View.AddBookView;
import View.CreateUserView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CreateUserController {
    
    CreateUserView CUView = new CreateUserView();
    
    public CreateUserController(){
    
        giveFunctions();
    }
    
    private void giveFunctions(){
    
        CUView.btnCreateUser.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
            
                try{
                
                    int identification = Integer.parseInt(CUView.txtIdentification.getText());
                    String username = CUView.txtUsername.getText();
                    String email = CUView.txtEmail.getText();
                    String status = CUView.chkActive.isSelected() ? "Activo" : "Inactivo";
                    
                    CreateUserModel userM = new CreateUserModel(identification, username, email, status);
                    
                    usuarioDAO uDAO = new usuarioDAO();
                    uDAO.AgregarUsuario(userM);
                    
                    JOptionPane.showMessageDialog(null, "User successfully created");
                    
                    CUView.txtIdentification.setText("");
                    CUView.txtUsername.setText("");
                    CUView.txtEmail.setText("");
                    
                }
                catch(NumberFormatException ex){System.out.println("The identification must be a number");}
                catch(Exception ex){System.out.println("Error: " + ex);}
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
