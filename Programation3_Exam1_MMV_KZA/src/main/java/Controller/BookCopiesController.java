package Controller;
import Model.BookManager;
import View.BookCopiesView;
import Model.AddBookModel;
import Persistence.ejemplarDAO;
import javax.swing.*;
import java.util.List;
/**
 *
 * @author karen
 */

public class BookCopiesController {

    private final BookCopiesView vista;
    private final ejemplarDAO dao;

    public BookCopiesController(BookCopiesView vista) {

        this.vista = vista;
        this.dao = new ejemplarDAO();

        configurarEventos();
        cargarEjemplares("");
    }

    private void configurarEventos() {

        vista.btnAgregar.addActionListener(e -> agregar());
        vista.btnConsultar.addActionListener(e -> consultar());
        vista.btnModificar.addActionListener(e -> modificar());
        vista.btnEliminar.addActionListener(e -> eliminar());
        vista.btnLimpiar.addActionListener(e -> limpiar());
        vista.btnReturn.addActionListener(e-> {new LibraryController(); vista.setVisible(false); vista.dispose();});

        vista.txtBuscar.addActionListener(e -> consultar());

        vista.tblCopias.getSelectionModel()
            .addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    cargarSeleccion();
                }
            });
    }

    private void agregar() {

        String codigo = vista.txtCodigo.getText().trim();
        AddBookModel libro =
            (AddBookModel) vista.cmbLibro.getSelectedItem();
        String estado =
            (String) vista.cmbEstado.getSelectedItem();

        if (codigo.isEmpty() || libro == null || estado == null) {
            mensaje("Complete todos los campos.");
            return;
        }

        // Verificar si el código ya existe en la base de datos.
        if (dao.ObtenerEjemplar(codigo) != null) {
            mensaje("Ya existe un ejemplar con ese código.");
            return;
        }

        BookManager ejemplar =
            new BookManager(codigo, libro, estado);

        if (dao.AgregarEjemplar(ejemplar)) {
            mensaje("Ejemplar agregado correctamente.");
            limpiar();
            cargarEjemplares("");
        } else {
            mensaje("No se pudo agregar el ejemplar.");
        }
    }

    private void consultar() {

        String codigo = vista.txtBuscar.getText().trim();

        if (codigo.isEmpty()) {
            cargarEjemplares("");
            return;
        }

        BookManager ejemplar = dao.ObtenerEjemplar(codigo);

        if (ejemplar == null) {
            mensaje("No se encontró un ejemplar con ese código.");
            return;
        }

        mostrarEjemplar(ejemplar);
        cargarEjemplares(codigo);
    }

    private void modificar() {

        String codigo = vista.txtCodigo.getText().trim();
        AddBookModel libro =
            (AddBookModel) vista.cmbLibro.getSelectedItem();
        String estado =
            (String) vista.cmbEstado.getSelectedItem();

        if (codigo.isEmpty() || libro == null || estado == null) {
            mensaje("Seleccione un ejemplar y complete los campos.");
            return;
        }

        BookManager existente = dao.ObtenerEjemplar(codigo);

        if (existente == null) {
            mensaje("El ejemplar no existe en la base de datos.");
            return;
        }

        BookManager actualizado =
            new BookManager(codigo, libro, estado);

        if (dao.ModificarEjemplar(actualizado)) {
            mensaje("Ejemplar modificado correctamente.");
            limpiar();
            cargarEjemplares("");
        } else {
            mensaje("No se pudo modificar el ejemplar.");
        }
    }

    private void eliminar() {

        String codigo = vista.txtCodigo.getText().trim();

        if (codigo.isEmpty()) {
            mensaje("Seleccione un ejemplar para eliminar.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
            vista,
            "¿Está seguro de eliminar el ejemplar " + codigo + "?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        if (dao.BorrarEjemplar(codigo)) {
            mensaje("Ejemplar eliminado correctamente.");
            limpiar();
            cargarEjemplares("");
        } else {
            mensaje("No se pudo eliminar. Verifique si el ejemplar "
                    + "está asociado a un préstamo.");
        }
    }

    private void cargarEjemplares(String texto) {

        List<BookManager> ejemplares = dao.ListaEjemplar(texto);

        vista.modeloTabla.setRowCount(0);

        for (BookManager ejemplar : ejemplares) {

            AddBookModel libro = ejemplar.getLibro();

            vista.modeloTabla.addRow(new Object[]{
                ejemplar.getCodigoEjemplar(),
                libro != null ? libro.getbookCode() : "",
                libro != null ? libro.getbookName() : "",
                ejemplar.getEstado()
            });
        }
    }

    private void cargarSeleccion() {

        int fila = vista.tblCopias.getSelectedRow();

        if (fila < 0) {
            return;
        }

        String codigo = vista.modeloTabla
            .getValueAt(fila, 0).toString();

        BookManager ejemplar = dao.ObtenerEjemplar(codigo);

        if (ejemplar != null) {
            mostrarEjemplar(ejemplar);
        }
    }

    
private void mostrarEjemplar(BookManager ejemplar) {

    vista.txtCodigo.setText(ejemplar.getCodigoEjemplar());
    vista.cmbEstado.setSelectedItem(ejemplar.getEstado());

    AddBookModel libroBuscado = ejemplar.getLibro();

    if (libroBuscado != null) {
        for (int i = 0; i < vista.cmbLibro.getItemCount(); i++) {

            AddBookModel libroCombo = vista.cmbLibro.getItemAt(i);

            if (libroCombo.getbookCode().equals(
                    libroBuscado.getbookCode())) {

                vista.cmbLibro.setSelectedIndex(i);
                break;
            }
        }
    }

    vista.txtCodigo.setEditable(false);
}


    private void limpiar() {
        vista.limpiarFormulario();
        cargarEjemplares("");
    }

    private void mensaje(String texto) {
        JOptionPane.showMessageDialog(vista, texto);
    }
}