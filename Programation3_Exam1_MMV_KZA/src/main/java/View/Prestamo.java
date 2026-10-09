package View;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Prestamo extends JFrame{
    
    JPanel panel;
    public JButton btnAddBook;
    public JTextField txtBCode;
    //public JCalendar calendar;
    public JDateChooser prestamoDate, limiteDate, devolucionDate;
    public JRadioButton dButton, ndButton;
    public ButtonGroup buttonGroup;
    
    public Prestamo(){
        
        createWindow();
        createPanel();
        createLabel();
        createTextFields();
        createButton();
        createCalendars();
        createRadioButton();
        setVisible(true);
    }

    private void createWindow(){
    
        setSize(400, 750);
        setTitle("Loan");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void createPanel(){
    
        panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);
    }
    
    private void createLabel(){
    
        JLabel lbTitle = new JLabel("Add a Book");
        lbTitle.setBounds(150, 30, 150, 30);
        lbTitle.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbTitle);
        
        JLabel lbBCode = new JLabel("Book Code");
        lbBCode.setBounds(150, 100, 150, 30);
        lbBCode.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBCode);
        
        JLabel lbBLDate = new JLabel("Loan Date");
        lbBLDate.setBounds(150, 200, 150, 30);
        lbBLDate.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBLDate);
        
        JLabel lbBLiDate = new JLabel("Limit Date");
        lbBLiDate.setBounds(130, 300, 150, 30);
        lbBLiDate.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBLiDate);
        
        JLabel lbBRDate = new JLabel("Return Date");
        lbBRDate.setBounds(130, 400, 150, 30);
        lbBRDate.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBRDate);
        
        JLabel lbBState= new JLabel("Return Date");
        lbBState.setBounds(130, 500, 150, 30);
        lbBState.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBState);
    }
    
    private void createTextFields(){
    
        txtBCode = new JTextField();
        txtBCode.setBounds(115, 160, 150, 30);
        panel.add(txtBCode);
    }
    
    private void createCalendars(){
    
        prestamoDate = new JDateChooser();
        prestamoDate.setDateFormatString("dd/MM/yyyy");
        prestamoDate.setBounds(115, 250, 150, 30); 
        panel.add(prestamoDate);
        
        limiteDate = new JDateChooser();
        limiteDate.setDateFormatString("dd/MM/yyyy");
        limiteDate.setBounds(115, 350, 150, 30); 
        panel.add(limiteDate);
        
        devolucionDate = new JDateChooser();
        devolucionDate.setDateFormatString("dd/MM/yyyy");
        devolucionDate.setBounds(115, 450, 150, 30); 
        panel.add(devolucionDate);
    }
    
    private void createRadioButton(){
    
        dButton = new JRadioButton("Available");
        ndButton = new JRadioButton("No Available");
        
        buttonGroup = new ButtonGroup();
        buttonGroup.add(dButton);
        buttonGroup.add(ndButton);
        
        dButton.setBounds(115, 550, 150, 30);
        panel.add(dButton);
        
        ndButton.setBounds(115, 580, 150, 30);
        panel.add(ndButton);
    }
    
    private void createButton(){
    
        btnAddBook = new JButton("Add Loan");
        btnAddBook.setBounds(115, 650, 150, 30);
        panel.add(btnAddBook);
    }
}
