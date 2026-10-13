package View;

import com.toedter.calendar.JDateChooser;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class LibraryView extends JFrame{
    
    
    JPanel panel;
    public JButton btnAddBook, btnPrestamo, btnCreateUser;
    public JTable table;
    public DefaultTableModel tableModel;
    
    public LibraryView(){
        
        createWindow();
        createPanel();
        createTable();
        createButtons();
        
        setVisible(true);
    }

    private void createWindow(){
    
        setSize(800, 800);
        setTitle("Library");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void createPanel(){
    
        panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);
    }
    
    private void createTable(){
    
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ISBN");
        tableModel.addColumn("Title");
        tableModel.addColumn("Author");
        tableModel.addColumn("Year");
        
        table = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 700, 500);
        panel.add(scrollPane);
    }
    
    private void createButtons(){
    
        btnAddBook = new JButton("Add Book");
        btnAddBook.setBounds(220, 600, 150, 30);
        panel.add(btnAddBook);
        
        btnPrestamo = new JButton("Loan");
        btnPrestamo.setBounds(420, 600, 150, 30);
        panel.add(btnPrestamo);
        
        btnCreateUser = new JButton("Create User");
        btnCreateUser.setBounds(320, 700, 150, 30);
        panel.add(btnCreateUser);
    }
}
