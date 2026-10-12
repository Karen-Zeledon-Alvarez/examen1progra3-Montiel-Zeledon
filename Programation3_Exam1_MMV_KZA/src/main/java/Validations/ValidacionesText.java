/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validations;

/**
 *
 * @author karen
 */
public class ValidacionesText {
    
     public static boolean soloNumeros(String texto) {
        return texto != null && texto.matches("\\d+");
    }
     
     public static boolean validarCorreo(String correo) {
        if (correo == null) {
            return false;
        }

        return correo.matches(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        );
    }
     
     public static boolean validarIdentificacion(String identificacion) {
        return identificacion != null
                && identificacion.matches("\\d{9}");
    }
}
