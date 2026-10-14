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

public class PrestamoView extends JFrame{
    
    JPanel panel;
    public JButton btnAddBook, btnReturn;
    public JTextField txtBCode, txtUCode;
    //public JCalendar calendar;
    public JDateChooser prestamoDate, limiteDate, devolucionDate;
    public JRadioButton dButton, ndButton;
    public ButtonGroup buttonGroup;
    
    public PrestamoView(){
        
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
    
        setSize(400, 810);
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
    
        JLabel lbTitle = new JLabel("Add a Book for loan");
        lbTitle.setBounds(150, 30, 150, 30);
        lbTitle.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbTitle);
        
        JLabel lbUCode = new JLabel("User ID");
        lbUCode.setBounds(150, 70, 150, 30);
        lbUCode.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbUCode);
        
        JLabel lbBCode = new JLabel("Book Code");
        lbBCode.setBounds(150, 160, 150, 30);
        lbBCode.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBCode);
        
        JLabel lbBLDate = new JLabel("Loan Date");
        lbBLDate.setBounds(150, 260, 150, 30);
        lbBLDate.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBLDate);
        
        JLabel lbBLiDate = new JLabel("Limit Date");
        lbBLiDate.setBounds(130, 360, 150, 30);
        lbBLiDate.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBLiDate);
        
        JLabel lbBRDate = new JLabel("Return Date");
        lbBRDate.setBounds(130, 460, 150, 30);
        lbBRDate.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBRDate);
        
        JLabel lbBState= new JLabel("State");
        lbBState.setBounds(130, 560, 150, 30);
        lbBState.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBState);
    }
    
    private void createTextFields(){
    
        txtUCode = new JTextField();
        txtUCode.setBounds(115, 100, 150, 30);
        panel.add(txtUCode);
    
        txtBCode = new JTextField();
        txtBCode.setBounds(115, 220, 150, 30);
        panel.add(txtBCode);
    }
    
    private void createCalendars(){
    
        prestamoDate = new JDateChooser();
        prestamoDate.setDateFormatString("dd/MM/yyyy");
        prestamoDate.setBounds(115, 310, 150, 30); 
        panel.add(prestamoDate);
        
        limiteDate = new JDateChooser();
        limiteDate.setDateFormatString("dd/MM/yyyy");
        limiteDate.setBounds(115, 410, 150, 30); 
        panel.add(limiteDate);
        
        devolucionDate = new JDateChooser();
        devolucionDate.setDateFormatString("dd/MM/yyyy");
        devolucionDate.setBounds(115, 510, 150, 30); 
        panel.add(devolucionDate);
    }
    
    private void createRadioButton(){
    
        dButton = new JRadioButton("Available");
        ndButton = new JRadioButton("No Available");
        
        buttonGroup = new ButtonGroup();
        buttonGroup.add(dButton);
        buttonGroup.add(ndButton);
        
        dButton.setBounds(115, 610, 150, 30);
        panel.add(dButton);
        
        ndButton.setBounds(115, 640, 150, 30);
        panel.add(ndButton);
    }
    
    private void createButton(){
    
        btnAddBook = new JButton("Add Loan");
        btnAddBook.setBounds(115, 710, 150, 30);
        panel.add(btnAddBook);
        
        btnReturn = new JButton("Return");
        btnReturn.setBounds(10, 10, 100, 30);
        panel.add(btnReturn);
    }
}