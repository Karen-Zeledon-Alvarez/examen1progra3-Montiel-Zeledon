package View;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class CreateUserView extends JFrame{
    
    JPanel panel;
    public JButton btnCreateUser, btnReturn;
    public JTextField txtUsername, txtEmail, txtIdentification;
    public JCheckBox chkActive;
    
    public CreateUserView(){
        
        createWindow();
        createPanel();
        createLabel();
        createTextFields();
        createButton();
    }

    private void createWindow(){
    
        setSize(400, 500);
        setTitle("Create User");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void createPanel(){
    
        panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);
    }
    
    private void createLabel(){
    
        JLabel lbTitle = new JLabel("Create User");
        lbTitle.setBounds(150, 30, 150, 30);
        lbTitle.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbTitle);
        
        JLabel lbIdentification = new JLabel("Identification");
        lbIdentification.setBounds(150, 120, 150, 30);
        lbIdentification.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbIdentification);
        
        JLabel lbBName = new JLabel("Username");
        lbBName.setBounds(150, 200, 150, 30);
        lbBName.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBName);
        
        JLabel lbBEMail = new JLabel("Email");
        lbBEMail.setBounds(150, 280, 150, 30);
        lbBEMail.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBEMail);
        
    }
    
    private void createTextFields(){
    
        txtIdentification = new JTextField();
        txtIdentification.setBounds(115, 150, 150, 30);
        panel.add(txtIdentification);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(115, 240, 150, 30);
        panel.add(txtUsername);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(115, 310, 150, 30);
        panel.add(txtEmail);
    }
    
    private void createButton(){
    
        btnCreateUser = new JButton("Create Username");
        btnCreateUser.setBounds(115, 350, 150, 30);
        panel.add(btnCreateUser);
        
        chkActive = new JCheckBox("Active");
        chkActive.setBounds(115, 400, 150, 30);
        chkActive.setSelected(true);
        panel.add(chkActive);
        
        btnReturn = new JButton("Return");
        btnReturn.setBounds(10, 10, 100, 30);
        panel.add(btnReturn);
    }
}
