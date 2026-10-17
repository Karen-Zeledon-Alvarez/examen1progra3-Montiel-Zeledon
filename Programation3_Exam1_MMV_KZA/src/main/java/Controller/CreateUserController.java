
package Controller;

import Model.CreateUserModel;
import Persistence.usuarioDAO;
import View.CreateUserView;
import javax.swing.*;
import java.util.List;
import Validations.ValidacionesText;

public class CreateUserController {

    private final CreateUserView vista;
    private final usuarioDAO dao;

    public CreateUserController() {
        vista = new CreateUserView();
        dao = new usuarioDAO();

        configurarEventos();
        cargarUsuarios("");
        vista.setVisible(true);
    }

    private void configurarEventos() {
        vista.btnCreateUser.addActionListener(e -> agregar());
        vista.btnConsultar.addActionListener(e -> consultar());
        vista.btnModificar.addActionListener(e -> modificar());
        vista.btnEliminar.addActionListener(e -> eliminar());
        vista.btnLimpiar.addActionListener(e -> limpiar());

        vista.btnBuscar.addActionListener(e ->
            cargarUsuarios(vista.txtBuscar.getText().trim())
        );

        vista.btnMostrarTodos.addActionListener(e -> {
            vista.txtBuscar.setText("");
            cargarUsuarios("");
        });

        vista.btnReturn.addActionListener(e -> vista.dispose());

        vista.tblUsuarios.getSelectionModel()
            .addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    cargarSeleccion();
                }
            });
    }

    private void agregar() {
        String identificacion =
            vista.txtIdentification.getText().trim();
        String nombre = vista.txtUsername.getText().trim();
        String correo = vista.txtEmail.getText().trim();

        if (!validarDatos(identificacion, nombre, correo)) {
            return;
        }

        int id = Integer.parseInt(identificacion);

        if (dao.ObtenerUsuario(id) != null) {
            JOptionPane.showMessageDialog(
                vista, "La identificación ya está registrada."
            );
            return;
        }

        String estado = vista.chkActive.isSelected()
                ? "Activo" : "Inactivo";

        CreateUserModel usuario =
            new CreateUserModel(id, nombre, correo, estado);

        if (dao.AgregarUsuario(usuario)) {
            JOptionPane.showMessageDialog(
                vista, "Usuario registrado correctamente."
            );
            limpiar();
        } else {
            JOptionPane.showMessageDialog(
                vista, "No se pudo registrar el usuario."
            );
        }
    }

    private void consultar() {
        String texto = vista.txtIdentification.getText().trim();

        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(
                vista, "Ingrese una identificación."
            );
            return;
        }

        if (!ValidacionesText.validarIdentificacion(texto)) {
            JOptionPane.showMessageDialog(
                vista, "La identificación debe tener 9 dígitos."
            );
            return;
        }

        CreateUserModel usuario =
            dao.ObtenerUsuario(Integer.parseInt(texto));

        if (usuario == null) {
            JOptionPane.showMessageDialog(
                vista, "No se encontró el usuario."
            );
            return;
        }

        mostrarUsuario(usuario);
    }

    private void modificar() {
        String texto = vista.txtIdentification.getText().trim();
        String nombre = vista.txtUsername.getText().trim();
        String correo = vista.txtEmail.getText().trim();

        if (!validarDatos(texto, nombre, correo)) {
            return;
        }

        int id = Integer.parseInt(texto);

        if (dao.ObtenerUsuario(id) == null) {
            JOptionPane.showMessageDialog(
                vista, "El usuario no existe."
            );
            return;
        }

        String estado = vista.chkActive.isSelected()
                ? "Activo" : "Inactivo";

        CreateUserModel usuario =
            new CreateUserModel(id, nombre, correo, estado);

        try {
            if (dao.ModificarUsuario(usuario)) {
                JOptionPane.showMessageDialog(
                    vista, "Usuario actualizado correctamente."
                );
                limpiar();
            } else {
                JOptionPane.showMessageDialog(
                    vista, "No se pudo actualizar el usuario."
                );
            }
        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(
                vista, "Error al modificar: " + ex.getMessage()
            );
        }
    }

    private void eliminar() {
        String texto = vista.txtIdentification.getText().trim();

        if (!ValidacionesText.validarIdentificacion(texto)) {
            JOptionPane.showMessageDialog(
                vista, "Seleccione un usuario con identificación válida."
            );
            return;
        }

        int id = Integer.parseInt(texto);
        CreateUserModel usuario = dao.ObtenerUsuario(id);

        if (usuario == null) {
            JOptionPane.showMessageDialog(
                vista, "El usuario no existe."
            );
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
            vista,
            "¿Desea eliminar a " + usuario.getUsername() + "?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            if (dao.BorrarUsuario(id)) {
                JOptionPane.showMessageDialog(
                    vista, "Usuario eliminado correctamente."
                );
                limpiar();
            } else {
                JOptionPane.showMessageDialog(
                    vista, "No se pudo eliminar el usuario."
                );
            }
        }
    }

    private void cargarUsuarios(String texto) {
        List<CreateUserModel> usuarios = dao.ListaUsuario(texto);

        vista.modeloTabla.setRowCount(0);

        for (CreateUserModel usuario : usuarios) {
            vista.modeloTabla.addRow(new Object[]{
                usuario.getIdentification(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getStatus()
            });
        }
    }

    private void cargarSeleccion() {
        int fila = vista.tblUsuarios.getSelectedRow();

        if (fila == -1) {
            return;
        }

        fila = vista.tblUsuarios.convertRowIndexToModel(fila);

        int id = Integer.parseInt(
            vista.modeloTabla.getValueAt(fila, 0).toString()
        );

        CreateUserModel usuario = dao.ObtenerUsuario(id);

        if (usuario != null) {
            mostrarUsuario(usuario);
        }
    }

    private void mostrarUsuario(CreateUserModel usuario) {
        vista.txtIdentification.setText(
            String.valueOf(usuario.getIdentification())
        );
        vista.txtUsername.setText(usuario.getUsername());
        vista.txtEmail.setText(usuario.getEmail());

        vista.chkActive.setSelected(
            "Activo".equalsIgnoreCase(usuario.getStatus())
        );

        vista.txtIdentification.setEnabled(false);
    }

    private boolean validarDatos(
            String identificacion, String nombre, String correo) {

        if (identificacion.isEmpty()
                || nombre.isEmpty()
                || correo.isEmpty()) {
            JOptionPane.showMessageDialog(
                vista, "Todos los campos son obligatorios."
            );
            return false;
        }

        if (!ValidacionesText.validarIdentificacion(identificacion)) {
            JOptionPane.showMessageDialog(
                vista,
                "La identificación debe contener exactamente 9 dígitos."
            );
            return false;
        }

        if (!ValidacionesText.validarCorreo(correo)) {
            JOptionPane.showMessageDialog(
                vista, "Ingrese un correo electrónico válido."
            );
            return false;
        }

        return true;
    }

    private void limpiar() {
        vista.limpiarFormulario();
        cargarUsuarios("");
    }
}
