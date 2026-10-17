
package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibraryView extends JFrame {

    public JButton btnAddBook;
    public JButton btnBookCopies;
    public JButton btnPrestamo;
    public JButton btnCreateUser;
    public JButton btnBuscar;
    public JButton btnMostrarTodos;

    public JTextField txtBuscar;
    public JTable table;
    public DefaultTableModel tableModel;

    public LibraryView() {

        setTitle("Library Management System");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createTable();
        createInterface();

        setVisible(true);
    }

    private void createInterface() {

        JPanel principal = new JPanel(new BorderLayout(15, 15));
        principal.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // Encabezado
        JLabel titulo = new JLabel("Library Management System");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.add(titulo, BorderLayout.WEST);

        // Búsqueda
        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));

        txtBuscar = new JTextField(25);
        btnBuscar = new JButton("Search");
        btnMostrarTodos = new JButton("Show All");

        busqueda.add(new JLabel("ISBN or title:"));
        busqueda.add(txtBuscar);
        busqueda.add(btnBuscar);
        busqueda.add(btnMostrarTodos);

        JPanel superior = new JPanel(new BorderLayout(10, 10));
        superior.add(encabezado, BorderLayout.NORTH);
        superior.add(busqueda, BorderLayout.SOUTH);

        // Botones de administración
        JPanel acciones = new JPanel(new GridLayout(2, 2, 10, 10));

        btnAddBook = new JButton("Add Book");
        btnBookCopies = new JButton("Manage Copies");
        btnPrestamo = new JButton("Loans");
        btnCreateUser = new JButton("Create User");

        acciones.add(btnAddBook);
        acciones.add(btnBookCopies);
        acciones.add(btnPrestamo);
        acciones.add(btnCreateUser);

        // Distribución principal
        principal.add(superior, BorderLayout.NORTH);
        principal.add(new JScrollPane(table), BorderLayout.CENTER);
        principal.add(acciones, BorderLayout.SOUTH);

        add(principal);
    }

    private void createTable() {

        tableModel = new DefaultTableModel(
            new Object[]{"ISBN", "Title", "Author", "Year"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        table.setRowHeight(25);
        table.getTableHeader().setReorderingAllowed(false);
    }
}

