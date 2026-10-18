
package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CreateUserView extends JFrame {

    private JPanel panelPrincipal;
    private JPanel panelFormulario;
    private JPanel panelBotones;

    public JTextField txtIdentification;
    public JTextField txtUsername;
    public JTextField txtEmail;
    public JTextField txtBuscar;

    public JCheckBox chkActive;

    public JButton btnCreateUser;
    public JButton btnConsultar;
    public JButton btnModificar;
    public JButton btnEliminar;
    public JButton btnLimpiar;
    public JButton btnReturn;
    public JButton btnBuscar;
    public JButton btnMostrarTodos;

    public JTable tblUsuarios;
    public DefaultTableModel modeloTabla;

    public CreateUserView() {
        createWindow();
        createComponents();
        createLayout();
    }

    private void createWindow() {
        setTitle("Administración de usuarios");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void createComponents() {
        // Campos del formulario
        txtIdentification = new JTextField();
        txtUsername = new JTextField();
        txtEmail = new JTextField();
        txtBuscar = new JTextField(20);

        chkActive = new JCheckBox("Usuario activo");
        chkActive.setSelected(true);

        // Botones
        btnCreateUser = new JButton("Agregar");
        btnConsultar = new JButton("Consultar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnReturn = new JButton("Return");
        btnBuscar = new JButton("Buscar");
        btnMostrarTodos = new JButton("Mostrar todos");

        // Tabla de usuarios
        modeloTabla = new DefaultTableModel(
            new Object[]{"Identificación", "Usuario", "Correo", "Estado"}, 0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tblUsuarios = new JTable(modeloTabla);
        tblUsuarios.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );
        tblUsuarios.setAutoCreateRowSorter(true);
    }

    private void createLayout() {
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // Encabezado
        JLabel lbTitulo = new JLabel(
            "Administración de usuarios", SwingConstants.CENTER
        );
        lbTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.add(btnReturn, BorderLayout.WEST);
        panelTitulo.add(lbTitulo, BorderLayout.CENTER);

        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);

        // Formulario
        panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        panelFormulario.setBorder(
            BorderFactory.createTitledBorder("Datos del usuario")
        );

        panelFormulario.add(new JLabel("Identificación:"));
        panelFormulario.add(txtIdentification);

        panelFormulario.add(new JLabel("Nombre de usuario:"));
        panelFormulario.add(txtUsername);

        panelFormulario.add(new JLabel("Correo electrónico:"));
        panelFormulario.add(txtEmail);

        panelFormulario.add(new JLabel("Estado:"));
        panelFormulario.add(chkActive);

        // Botones del formulario
        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        panelBotones.add(btnCreateUser);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5));
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        // Búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBorder(
            BorderFactory.createTitledBorder("Buscar usuarios")
        );
        panelBusqueda.add(new JLabel("Identificación o nombre:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnMostrarTodos);

        // Tabla
        JScrollPane scrollTabla = new JScrollPane(tblUsuarios);
        scrollTabla.setBorder(
            BorderFactory.createTitledBorder("Usuarios registrados")
        );

        JPanel panelCentral = new JPanel(new BorderLayout(8, 8));
        panelCentral.add(panelSuperior, BorderLayout.NORTH);
        panelCentral.add(panelBusqueda, BorderLayout.CENTER);
        panelCentral.add(scrollTabla, BorderLayout.SOUTH);

        // Ajustar el espacio de la tabla y el formulario
        panelCentral.add(scrollTabla, BorderLayout.CENTER);
        panelCentral.add(panelBusqueda, BorderLayout.SOUTH);

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        add(panelPrincipal);
    }

    public void limpiarFormulario() {
        txtIdentification.setText("");
        txtUsername.setText("");
        txtEmail.setText("");
        txtBuscar.setText("");
        chkActive.setSelected(true);
        txtIdentification.setEnabled(true);
        tblUsuarios.clearSelection();
    }
}
