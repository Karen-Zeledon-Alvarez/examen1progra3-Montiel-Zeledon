package View;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class CreateUserView extends JFrame{
    
    JPanel panel;
    public JButton btnCreateUser, btnReturn;
    public JTextField txtUsername, txtEmail;
    
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
        
        JLabel lbBName = new JLabel("Username");
        lbBName.setBounds(150, 150, 150, 30);
        lbBName.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBName);
        
        JLabel lbBEMail = new JLabel("Email");
        lbBEMail.setBounds(150, 250, 150, 30);
        lbBEMail.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBEMail);
    }
    
    private void createTextFields(){
    
        txtUsername = new JTextField();
        txtUsername.setBounds(115, 190, 150, 30);
        panel.add(txtUsername);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(115, 290, 150, 30);
        panel.add(txtEmail);
    }
    
    private void createButton(){
    
        btnCreateUser = new JButton("Create Username");
        btnCreateUser.setBounds(115, 350, 150, 30);
        panel.add(btnCreateUser);
        
        btnReturn = new JButton("Return");
        btnReturn.setBounds(10, 10, 100, 30);
        panel.add(btnReturn);
    }
}
