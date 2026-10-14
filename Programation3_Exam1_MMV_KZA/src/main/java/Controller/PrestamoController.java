package Controller;

import Model.BookManager;
import Model.CreateUserModel;
import Model.PrestamoModel;
import Persistence.ejemplarDAO;
import Persistence.prestamoDAO;
import Persistence.usuarioDAO;
import View.PrestamoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JOptionPane;

public class PrestamoController {
    
    PrestamoView PView = new PrestamoView();
    PrestamoModel PModel = new PrestamoModel();
    prestamoDAO pDao = new prestamoDAO();
    usuarioDAO uDao = new usuarioDAO();
    ejemplarDAO eDao = new ejemplarDAO();
    
    public PrestamoController(){
        giveFunctions();
    }
    
    private void giveFunctions(){
    
        PView.btnAddBook.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
            
                try {
                    int getUserID = Integer.parseInt(PView.txtUCode.getText().trim());
                    String getBookID = PView.txtBCode.getText().trim(); 
                    
                    java.util.Date getLoanDate = PView.prestamoDate.getDate();
                    java.util.Date getLimitDate = PView.limiteDate.getDate();
                    java.util.Date getReturnDate = PView.devolucionDate.getDate();
                    
                    if (getLoanDate == null || getLimitDate == null || getReturnDate == null) {JOptionPane.showMessageDialog(PView, "Please select all the dates."); return;}
                    
                    String state;
                    if (PView.dButton.isSelected()) { state = "Available"; }
                    else if (PView.ndButton.isSelected()) { state = "No Available"; }
                    else { JOptionPane.showMessageDialog(null, "Choose a State"); return; }
                    
                    CreateUserModel userObj = uDao.ObtenerUsuario(getUserID);
                    BookManager bookObj = eDao.ObtenerEjemplar(getBookID);
                    
                    if (userObj == null) {JOptionPane.showMessageDialog(PView, "The user does not exist in the database."); return;}
                    if (bookObj == null) {JOptionPane.showMessageDialog(PView, "The copy of the book does not exist."); return;}
                    
                    PModel.setUserCode(userObj);
                    PModel.setBookCode(bookObj);
                    PModel.setPrestamoDate(new Date(getLoanDate.getTime()));
                    PModel.setLimitDate(new Date(getLimitDate.getTime()));
                    PModel.setReturnDate(new Date(getReturnDate.getTime()));
                    PModel.setState(state);
                    
                    boolean success = pDao.AgregarPrestamo(PModel);
                    
                    if (success) {JOptionPane.showMessageDialog(PView, "Loan Successfully Added");}
                    else {JOptionPane.showMessageDialog(PView, "Error saving the loan to the database");}
                    
                } catch (NumberFormatException ex) {JOptionPane.showMessageDialog(PView, "The user ID must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);}
            }
        });
        
        PView.btnReturn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                PView.setVisible(false);
                PView.dispose();
                LibraryController LController = new LibraryController();              
            }
        });    
    }
}