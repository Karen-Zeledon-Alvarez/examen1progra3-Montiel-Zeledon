package View;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddBook extends JFrame{
    
    JPanel panel;
    public JButton btnAddBook;
    public JTextField txtISBN, txtTitulo, txtAutor, txtAnio;
    
    public AddBook(){
        
        createWindow();
        createPanel();
        createLabel();
        createTextFields();
        createButton();
    }

    private void createWindow(){
    
        setSize(400, 600);
        setTitle("Add Book");
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
    
        JLabel lbTitle = new JLabel("Add a Book");
        lbTitle.setBounds(150, 30, 150, 30);
        lbTitle.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbTitle);
        
        JLabel lbBName = new JLabel("Book Name");
        lbBName.setBounds(150, 100, 150, 30);
        lbBName.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBName);
        
        JLabel lbBCode = new JLabel("Book Code");
        lbBCode.setBounds(150, 200, 150, 30);
        lbBCode.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBCode);
        
        JLabel lbBAutor = new JLabel("Author`s Book");
        lbBAutor.setBounds(130, 300, 150, 30);
        lbBAutor.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBAutor);
        
        JLabel lbBAnio = new JLabel("Year");
        lbBAnio.setBounds(130, 400, 150, 30);
        lbBAnio.setFont(new Font("Times New Roman", 3, 18));
        panel.add(lbBAnio);
    }
    
    private void createTextFields(){
    
        txtTitulo = new JTextField();
        txtTitulo.setBounds(115, 160, 150, 30);
        panel.add(txtTitulo);
        
        txtISBN = new JTextField();
        txtISBN.setBounds(115, 260, 150, 30);
        panel.add(txtISBN);
        
        txtAutor = new JTextField();
        txtAutor.setBounds(115, 360, 150, 30);
        panel.add(txtAutor);
        
        txtAnio = new JTextField();
        txtAnio.setBounds(115, 460, 150, 30);
        panel.add(txtAnio);
    }
    
    private void createButton(){
    
        btnAddBook = new JButton("Add Book");
        btnAddBook.setBounds(115, 510, 150, 30);
        panel.add(btnAddBook);
    }
}
