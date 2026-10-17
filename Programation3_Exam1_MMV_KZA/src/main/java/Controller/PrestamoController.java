package Controller;

import Model.BookManager;
import Model.CreateUserModel;
import Model.PrestamoModel;
import Persistence.ejemplarDAO;
import Persistence.prestamoDAO;
import Persistence.usuarioDAO;
import View.PrestamoView;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class PrestamoController {

    private final PrestamoView vista;
    private final prestamoDAO pDao;
    private final usuarioDAO uDao;
    private final ejemplarDAO eDao;

    public PrestamoController() {
        vista = new PrestamoView();

        pDao = new prestamoDAO();
        uDao = new usuarioDAO();
        eDao = new ejemplarDAO();

        configurarEventos();
        cargarEjemplaresDisponibles();
        cargarPrestamos("");
        vista.setVisible(true);
    }

    private void configurarEventos() {
        vista.btnAddBook.addActionListener(e -> agregar());
        vista.btnConsultar.addActionListener(e -> consultar());
        vista.btnModificar.addActionListener(e -> modificar());
        vista.btnEliminar.addActionListener(e -> eliminar());
        vista.btnLimpiar.addActionListener(e -> limpiar());

        vista.btnBuscar.addActionListener(e ->
            cargarPrestamos(vista.txtBuscar.getText().trim())
        );

        vista.txtBuscar.addActionListener(e ->
            cargarPrestamos(vista.txtBuscar.getText().trim())
        );

        vista.btnMostrarTodos.addActionListener(e -> {
            vista.txtBuscar.setText("");
            cargarPrestamos("");
        });

        vista.btnReturn.addActionListener(e -> {
            vista.dispose();
            new LibraryController();
        });

        vista.tblPrestamos.getSelectionModel()
            .addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    cargarSeleccion();
                }
            });
    }

    private void agregar() {
        String idUsuario = vista.txtUCode.getText().trim();
        BookManager ejemplar =
            (BookManager) vista.cmbEjemplares.getSelectedItem();

            if (ejemplar == null) {
                mostrarMensaje("Seleccione un ejemplar disponible.");
            return;
        }

        if (!validarDatos(idUsuario)) {
            return;
        }

        try {
            int id = Integer.parseInt(idUsuario);

            CreateUserModel usuario = uDao.ObtenerUsuario(id);
           

            if (usuario == null) {
                mostrarMensaje("El usuario no existe.");
                return;
            }

            if (ejemplar == null) {
                mostrarMensaje("El ejemplar no existe.");
                return;
            }

            if (vista.prestamoDate.getDate() == null
                    || vista.limiteDate.getDate() == null) {
                mostrarMensaje(
                    "Seleccione la fecha del préstamo y la fecha límite."
                );
                return;
            }

            Date fechaPrestamo = convertirFecha(
                vista.prestamoDate.getDate()
            );

            Date fechaLimite = convertirFecha(
                vista.limiteDate.getDate()
            );

            Date fechaDevolucion = vista.devolucionDate.getDate() == null
                ? null
                : convertirFecha(vista.devolucionDate.getDate());

            if (!validarFechas(
                    fechaPrestamo, fechaLimite, fechaDevolucion)) {
                return;
            }

            String estado = obtenerEstado();

            PrestamoModel prestamo = new PrestamoModel();
            prestamo.setUserCode(usuario);
            prestamo.setBookCode(ejemplar);
            prestamo.setPrestamoDate(fechaPrestamo);
            prestamo.setLimitDate(fechaLimite);
            prestamo.setReturnDate(fechaDevolucion);
            prestamo.setState(estado);

            if (pDao.AgregarPrestamo(prestamo)) {
                mostrarMensaje("Préstamo registrado correctamente.");
                limpiar();
            } else {
                mostrarMensaje(
                    "No se pudo registrar el préstamo."
                );
            }

        } catch (NumberFormatException ex) {
            mostrarMensaje("El ID del usuario debe ser numérico.");
        }
    }

    private void consultar() {
        String texto = vista.txtPrestamoCode.getText().trim();

        if (texto.isEmpty()) {
            mostrarMensaje("Seleccione un préstamo o ingrese su número.");
            return;
        }

        try {
            int id = Integer.parseInt(texto);
            PrestamoModel prestamo = pDao.ObtenerPrestamo(id);

            if (prestamo == null) {
                mostrarMensaje("No se encontró el préstamo.");
                return;
            }

            mostrarPrestamo(prestamo);

        } catch (NumberFormatException ex) {
            mostrarMensaje("El número del préstamo no es válido.");
        }
    }

    private void modificar() {
        String codigo = vista.txtPrestamoCode.getText().trim();
        String idUsuario = vista.txtUCode.getText().trim();
        BookManager ejemplar =
        (BookManager) vista.cmbEjemplares.getSelectedItem();

        if (ejemplar == null) {
            mostrarMensaje("Seleccione un ejemplar disponible.");
        return;
        }
        if (codigo.isEmpty()) {
            mostrarMensaje("Seleccione un préstamo de la tabla.");
            return;
        }

        if (!validarDatos(idUsuario)) {
            return;
        }

        if (vista.prestamoDate.getDate() == null
                || vista.limiteDate.getDate() == null) {
            mostrarMensaje(
                "Seleccione la fecha del préstamo y la fecha límite."
            );
            return;
        }

        try {
            int idPrestamo = Integer.parseInt(codigo);
            int idUser = Integer.parseInt(idUsuario);

            PrestamoModel existente = pDao.ObtenerPrestamo(idPrestamo);

            if (existente == null) {
                mostrarMensaje("El préstamo no existe.");
                return;
            }

            CreateUserModel usuario = uDao.ObtenerUsuario(idUser);
            

            if (usuario == null) {
                mostrarMensaje("El usuario no existe.");
                return;
            }

            if (ejemplar == null) {
                mostrarMensaje("El ejemplar no existe.");
                return;
            }

            Date fechaPrestamo =
                convertirFecha(vista.prestamoDate.getDate());

            Date fechaLimite =
                convertirFecha(vista.limiteDate.getDate());

            Date fechaDevolucion =
                vista.devolucionDate.getDate() == null
                ? null
                : convertirFecha(vista.devolucionDate.getDate());

            if (!validarFechas(
                    fechaPrestamo, fechaLimite, fechaDevolucion)) {
                return;
            }

            PrestamoModel prestamo = new PrestamoModel(
                idPrestamo,
                ejemplar,
                usuario,
                obtenerEstado(),
                fechaPrestamo,
                fechaLimite,
                fechaDevolucion
            );

            pDao.ModificarPrestamo(prestamo);

            mostrarMensaje(
                "Solicitud de actualización procesada. "
                + "Verifique el resultado en la tabla."
            );

            cargarPrestamos("");

        } catch (NumberFormatException ex) {
            mostrarMensaje("Revise los números ingresados.");
        } catch (Exception ex) {
            mostrarMensaje(
                "Error al modificar el préstamo: " + ex.getMessage()
            );
        }
    }

    private void eliminar() {
        String texto = vista.txtPrestamoCode.getText().trim();

        if (texto.isEmpty()) {
            mostrarMensaje("Seleccione un préstamo para eliminar.");
            return;
        }

        try {
            int id = Integer.parseInt(texto);
            PrestamoModel prestamo = pDao.ObtenerPrestamo(id);

            if (prestamo == null) {
                mostrarMensaje("El préstamo no existe.");
                return;
            }

            int respuesta = JOptionPane.showConfirmDialog(
                vista,
                "¿Desea eliminar el préstamo número " + id + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                pDao.BorrarPrestamo(id);

                mostrarMensaje(
                    "Solicitud de eliminación procesada. "
                    + "Verifique el resultado en la tabla."
                );

                limpiar();
            }

        } catch (NumberFormatException ex) {
            mostrarMensaje("El número del préstamo no es válido.");
        } catch (Exception ex) {
            mostrarMensaje(
                "Error al eliminar el préstamo: " + ex.getMessage()
            );
        }
    }

    private void cargarPrestamos(String texto) {
        List<PrestamoModel> prestamos = pDao.ListaPrestamo(texto);

        vista.modeloTabla.setRowCount(0);

        for (PrestamoModel p : prestamos) {
            vista.modeloTabla.addRow(new Object[]{
                p.getPrestamoCode(),
                p.getBookCode() == null
                    ? "" : p.getBookCode().getCodigoEjemplar(),
                p.getUserCode() == null
                    ? "" : p.getUserCode().getIdentification(),
                p.getState(),
                p.getPrestamoDate(),
                p.getLimitDate(),
                p.getReturnDate()
            });
        }
    }

    private void cargarSeleccion() {
        int fila = vista.tblPrestamos.getSelectedRow();

        if (fila == -1) {
            return;
        }

        fila = vista.tblPrestamos.convertRowIndexToModel(fila);

        int id = Integer.parseInt(
            vista.modeloTabla.getValueAt(fila, 0).toString()
        );

        PrestamoModel prestamo = pDao.ObtenerPrestamo(id);

        if (prestamo != null) {
            mostrarPrestamo(prestamo);
        }
    }

    private void mostrarPrestamo(PrestamoModel p) {
        vista.txtPrestamoCode.setText(
            String.valueOf(p.getPrestamoCode())
        );

        vista.txtUCode.setText(
            p.getUserCode() == null
                ? ""
                : String.valueOf(p.getUserCode().getIdentification())
        );
        vista.cmbEjemplares.setSelectedItem(p.getBookCode());

        vista.prestamoDate.setDate(p.getPrestamoDate());
        vista.limiteDate.setDate(p.getLimitDate());
        vista.devolucionDate.setDate(p.getReturnDate());

        if ("Activo".equalsIgnoreCase(p.getState())) {
            vista.dButton.setSelected(true);
        } else {
            vista.ndButton.setSelected(true);
        }
    }

    private boolean validarDatos(
            String idUsuario) {

        if (idUsuario.isEmpty()) {
            mostrarMensaje(
                "El ID del usuario y el código del ejemplar son obligatorios."
            );
            return false;
        }

        try {
            int id = Integer.parseInt(idUsuario);

            if (id <= 0) {
                mostrarMensaje("El ID del usuario debe ser positivo.");
                return false;
            }

        } catch (NumberFormatException ex) {
            mostrarMensaje("El ID del usuario debe ser numérico.");
            return false;
        }

        return true;
    }

    private boolean validarFechas(
            Date prestamo, Date limite, Date devolucion) {

        if (limite.before(prestamo)) {
            mostrarMensaje(
                "La fecha límite no puede ser anterior al préstamo."
            );
            return false;
        }

        if (devolucion != null && devolucion.before(prestamo)) {
            mostrarMensaje(
                "La devolución no puede ser anterior al préstamo."
            );
            return false;
        }

        return true;
    }

    private Date convertirFecha(java.util.Date fecha) {
        return new Date(fecha.getTime());
    }

    private String obtenerEstado() {
        return vista.dButton.isSelected() ? "Activo" : "Inactivo";
    }

    private void limpiar() {
        vista.limpiarFormulario();
        cargarPrestamos("");
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje);
    }
    
    private void cargarEjemplaresDisponibles() {
    vista.cmbEjemplares.removeAllItems();

    List<BookManager> ejemplares =
        eDao.ListarEjemplaresDisponibles();

    for (BookManager ejemplar : ejemplares) {
        vista.cmbEjemplares.addItem(ejemplar);
    }

    vista.cmbEjemplares.setSelectedIndex(-1);
}
}