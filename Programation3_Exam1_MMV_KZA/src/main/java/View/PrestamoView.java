package View;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Model.BookManager;

public class PrestamoView extends JFrame {

    private JPanel panelPrincipal;
    private JPanel panelFormulario;
    private JPanel panelBotones;

    public JTextField txtPrestamoCode;
    public JComboBox<BookManager> cmbEjemplares;
    public JTextField txtUCode;
    public JTextField txtBuscar;

    public JDateChooser prestamoDate;
    public JDateChooser limiteDate;
    public JDateChooser devolucionDate;

    public JRadioButton dButton;
    public JRadioButton ndButton;
    public ButtonGroup buttonGroup;

    public JButton btnAddBook;
    public JButton btnConsultar;
    public JButton btnModificar;
    public JButton btnEliminar;
    public JButton btnLimpiar;
    public JButton btnReturn;
    public JButton btnBuscar;
    public JButton btnMostrarTodos;
    public JButton btnPagarMulta;
    public JButton btnDeudores;

    public JTable tblPrestamos;
    public DefaultTableModel modeloTabla;

    public PrestamoView() {
        createWindow();
        createComponents();
        createLayout();
    }

    private void createWindow() {
        setTitle("Administración de préstamos");
        setSize(1000, 750);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void createComponents() {
        
        txtPrestamoCode = new JTextField();
        txtPrestamoCode.setEnabled(false);

        cmbEjemplares = new JComboBox<>(); cmbEjemplares.setRenderer(new DefaultListCellRenderer() {
              
        @Override
        public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof BookManager) {
            BookManager ejemplar = (BookManager) value;
            setText(ejemplar.getCodigoEjemplar());
        }

        return this;
        }});  
        
        txtUCode = new JTextField();
        txtBuscar = new JTextField(15);

        prestamoDate = new JDateChooser();
        limiteDate = new JDateChooser();
        devolucionDate = new JDateChooser();

        prestamoDate.setDateFormatString("dd/MM/yyyy");
        limiteDate.setDateFormatString("dd/MM/yyyy");
        devolucionDate.setDateFormatString("dd/MM/yyyy");

        dButton = new JRadioButton("Activo");
        ndButton = new JRadioButton("Inactivo");

        buttonGroup = new ButtonGroup();
        buttonGroup.add(dButton);
        buttonGroup.add(ndButton);

        dButton.setSelected(true);

        btnAddBook = new JButton("Agregar");
        btnConsultar = new JButton("Consultar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnReturn = new JButton("Regresar");
        btnBuscar = new JButton("Buscar");
        btnMostrarTodos = new JButton("Mostrar todos");
        btnPagarMulta = new JButton("Pagar multa");
        btnDeudores = new JButton("Ver deudores");

        modeloTabla = new DefaultTableModel(
            new Object[]{
                "N.º préstamo",
                "Código ejemplar",
                "ID usuario",
                "Estado",
                "Fecha préstamo",
                "Fecha límite",
                "Fecha devolución",
                "Multa"
            }, 0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {return false;}
        };

        tblPrestamos = new JTable(modeloTabla);
        tblPrestamos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblPrestamos.setAutoCreateRowSorter(true);
        tblPrestamos.setRowHeight(22);
    }

    private void createLayout() {
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // Encabezado
        JLabel lbTitulo = new JLabel("Administración de préstamos", SwingConstants.CENTER);
        lbTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel panelTitulo = new JPanel(new BorderLayout(10, 10));
        panelTitulo.add(btnReturn, BorderLayout.WEST);
        panelTitulo.add(lbTitulo, BorderLayout.CENTER);

        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);

        // Formulario
        panelFormulario = new JPanel(new GridLayout(0, 4, 8, 8));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del préstamo"));

        panelFormulario.add(new JLabel("N.º préstamo:"));
        panelFormulario.add(txtPrestamoCode);

        panelFormulario.add(new JLabel("ID del usuario:"));
        panelFormulario.add(txtUCode);

        panelFormulario.add(new JLabel("Ejemplar disponible:"));
        panelFormulario.add(cmbEjemplares);

        panelFormulario.add(new JLabel("Fecha del préstamo:"));
        panelFormulario.add(prestamoDate);

        panelFormulario.add(new JLabel("Fecha límite:"));
        panelFormulario.add(limiteDate);

        panelFormulario.add(new JLabel("Fecha de devolución:"));
        panelFormulario.add(devolucionDate);

        panelFormulario.add(new JLabel("Estado:"));

        JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelEstado.add(dButton);
        panelEstado.add(ndButton);

        panelFormulario.add(panelEstado);

        // Botones de operaciones
        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));

        panelBotones.add(btnAddBook);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnPagarMulta);
        panelBotones.add(btnDeudores);

        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5));
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        // Búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Buscar préstamos"));

        panelBusqueda.add(new JLabel("N.º préstamo, ejemplar, usuario o estado:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnMostrarTodos);

        // Tabla
        JScrollPane scrollTabla = new JScrollPane(tblPrestamos);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Préstamos registrados"));

        // Distribución central
        JPanel panelCentral = new JPanel(new BorderLayout(8, 8));
        panelCentral.add(panelSuperior, BorderLayout.NORTH);
        panelCentral.add(panelBusqueda, BorderLayout.CENTER);
        panelCentral.add(scrollTabla, BorderLayout.SOUTH);

        // Dar espacio suficiente a la tabla
        panelCentral.add(scrollTabla, BorderLayout.CENTER);
        panelCentral.add(panelBusqueda, BorderLayout.SOUTH);

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    public void limpiarFormulario() {
        
        txtPrestamoCode.setText("");
        txtUCode.setText("");
        cmbEjemplares.setSelectedIndex(-1);
        txtBuscar.setText("");

        prestamoDate.setDate(null);
        limiteDate.setDate(null);
        devolucionDate.setDate(null);

        dButton.setSelected(true);
        txtPrestamoCode.setEnabled(false);

        tblPrestamos.clearSelection();
    }
}