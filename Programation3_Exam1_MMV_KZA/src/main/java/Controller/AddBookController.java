package Controller;

import Model.AddBookModel;
import Persistence.LibroDAO;
import Validations.ValidacionesText;
import View.AddBookView;
import View.CreateUserView;
import View.LibraryView;
import View.PrestamoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class AddBookController {
    
    AddBookView ABView = new AddBookView();
    LibroDAO lDao = new LibroDAO();
    
    public AddBookController(){
    
        giveFunctions();
    }
    
    private void giveFunctions(){
    
        ABView.btnAddBook.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
                
                String yearText = ABView.txtAnio.getText();
                
                if (ValidacionesText.soloNumeros(yearText)) {
                
                    AddBookModel aBModel = new AddBookModel();
                
                    aBModel.setbookCode(ABView.txtISBN.getText());
                    aBModel.setbookName(ABView.txtTitulo.getText());
                    aBModel.setbookAuthor(ABView.txtAutor.getText());
                
                    int year = Integer.parseInt(yearText);
                    aBModel.setbookYear(year);
                
                    lDao.AgregarLibro(aBModel);
                }
                else{
                
                    JOptionPane.showMessageDialog(ABView, "Please, Insert a valid year (only numbers).", "Validation Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
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
