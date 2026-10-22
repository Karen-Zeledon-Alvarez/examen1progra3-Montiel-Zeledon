package Controller;

import Model.AddBookModel;
import Persistence.libroDAO;
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
    libroDAO lDao = new libroDAO();
    
    public AddBookController(){
    
        giveFunctions();
    }
    
    private void giveFunctions(){
    
        ABView.btnAddBook.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
                
                String saveCode = ABView.txtISBN.getText().trim();
                String saveTitle = ABView.txtTitulo.getText().trim();
                String saveAuthor = ABView.txtAutor.getText().trim();
                String yearText = ABView.txtAnio.getText().trim();
                
                if(saveCode.isEmpty() || saveTitle.isEmpty() || saveAuthor.isEmpty() || yearText.isEmpty()){
                    
                    JOptionPane.showMessageDialog(ABView, "Please, fill all gaps first.", "Validation Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
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
            }
        });    
    }
}
