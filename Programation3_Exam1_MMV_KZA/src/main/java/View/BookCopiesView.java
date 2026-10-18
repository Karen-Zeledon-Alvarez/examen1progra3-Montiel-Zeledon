package View;
import Model.AddBookModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author karen
 */
public class BookCopiesView extends JFrame {
    
    public JTextField txtCodigo;
    public JComboBox<AddBookModel> cmbLibro;
    public JComboBox<String> cmbEstado;
    public JTextField txtBuscar;

    public JButton btnAgregar;
    public JButton btnConsultar;
    public JButton btnModificar;
    public JButton btnEliminar;
    public JButton btnLimpiar;
    public JButton btnReturn;

    public JTable tblCopias;
    public DefaultTableModel modeloTabla;

    public BookCopiesView(List<AddBookModel> libros) {

        setTitle("Administración de ejemplares");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        crearFormulario(libros);
        crearTabla();

        setVisible(true);
    }

    private void crearFormulario(List<AddBookModel> libros) {

        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JPanel formulario = new JPanel(new GridLayout(3, 2, 8, 8));

        txtCodigo = new JTextField();
        cmbLibro = new JComboBox<>();
        cmbEstado = new JComboBox<>(
            new String[]{"Disponible", "Prestado", "Dañado"}
        );

        if (libros != null) {
            for (AddBookModel libro : libros) {
                cmbLibro.addItem(libro);
            }
        }

        formulario.add(new JLabel("Código del ejemplar:"));
        formulario.add(txtCodigo);

        formulario.add(new JLabel("Libro:"));
        formulario.add(cmbLibro);

        formulario.add(new JLabel("Estado:"));
        formulario.add(cmbEstado);

        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));

        txtBuscar = new JTextField(15);
        btnConsultar = new JButton("Consultar");

        busqueda.add(new JLabel("Buscar por código:"));
        busqueda.add(txtBuscar);
        busqueda.add(btnConsultar);

        JPanel botones = new JPanel(new BorderLayout());

        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnReturn = new JButton("Return");
        
        JPanel panelIzquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelIzquierda.add(btnReturn);

        JPanel panelCentro = new JPanel(new FlowLayout());
        panelCentro.add(btnAgregar);
        panelCentro.add(btnModificar);
        panelCentro.add(btnEliminar);
        panelCentro.add(btnLimpiar);

        botones.add(panelIzquierda, BorderLayout.WEST);
        botones.add(panelCentro, BorderLayout.CENTER);

        JPanel superior = new JPanel(new BorderLayout(8, 8));
        superior.add(formulario, BorderLayout.CENTER);
        superior.add(busqueda, BorderLayout.SOUTH);

        principal.add(superior, BorderLayout.NORTH);
        principal.add(botones, BorderLayout.CENTER);

        add(principal, BorderLayout.NORTH);
    }

    private void crearTabla() {

        modeloTabla = new DefaultTableModel(
            new Object[]{"Código", "ISBN", "Libro", "Estado"}, 0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tblCopias = new JTable(modeloTabla);
        tblCopias.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );
        tblCopias.setRowHeight(24);

        add(new JScrollPane(tblCopias), BorderLayout.CENTER);
    }

    public void limpiarFormulario() {
        txtCodigo.setText("");
        txtBuscar.setText("");
        cmbLibro.setSelectedIndex(-1);
        cmbEstado.setSelectedIndex(0);
        tblCopias.clearSelection();
        txtCodigo.setEditable(true);
        txtCodigo.requestFocus();
    }
}