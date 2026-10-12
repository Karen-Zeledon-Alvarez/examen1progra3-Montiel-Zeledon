/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;

import DataBaseConnection.BDConnection;
import Model.BookManager;
import Model.CreateUserModel;
import Model.PrestamoModel;
import Persistence.usuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author karen
 */
public class prestamoDAO {
    
    usuarioDAO usuario=new usuarioDAO();
    ejemplarDAO ejemplar=new ejemplarDAO();
    
    public boolean AgregarPrestamo(PrestamoModel p) {

        String sql = "INSERT INTO \"prestamo\" "
                + "(\"id\", \"ejemplar_codigo\", "
                + "\"usuario_id\", \"estado\","
                + "\"fecha_prestamo\",\"fecha_limite\","
                + "(\"fecha_devolucion\" ) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = BDConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getPrestamoCode());
            ps.setString(2, p.getBookCode().getCodigoEjemplar());
            ps.setInt(3, p.getUserCode().getIdentification());
            ps.setString(4, p.getState());
            ps.setDate(5, p.getPrestamoDate());
            ps.setDate(6, p.getLimitDate());
            ps.setDate(7, p.getReturnDate());
            

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println("Error al guardar el prestamo");
            e.printStackTrace();

            return false;
        }
    }
    public boolean BorrarPrestamo(int prestamoCode) throws SQLException {
        
        String sql = "DELETE FROM \"prestamo\" WHERE \"id\" = ?";
        
        try (Connection conn = BDConnection.conectar();
              PreparedStatement ps = conn.prepareStatement(sql)){               
                                          
            ps.setInt(1, prestamoCode);
            int filas = ps.executeUpdate();
            return filas > 0;
            
        }
         catch(Exception e){
            System.out.println("Error al intentar borrar el prestamo" + e.getMessage());
            e.printStackTrace();
             return false;
         }
        
    }
    
    public boolean ModificarPrestamo(PrestamoModel p) throws SQLException{
        
        String sql = "UPDATE \"prestamo\" SET \"ejemplar_codigo\" = ?, \"usuario_id\" = ?, \"estado\" = ?, \"fecha_prestamo\" = ?, \"fecha_limite\" = ?, \"fecha_devolucion\" = ? "
                   + "WHERE \"id\" = ?";
        
        try(Connection conn = BDConnection.conectar();
                
           PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, p.getBookCode().getCodigoEjemplar());
            ps.setInt(2, p.getUserCode().getIdentification());
            ps.setString(3, p.getState());
             ps.setDate(4, p.getPrestamoDate());
              ps.setDate(5, p.getLimitDate());
               ps.setDate(6, p.getReturnDate());
           
            
            int filas = ps.executeUpdate();
            return filas > 0;
              
           }catch(SQLException e){
            System.out.println("Ocurrio un error al actualizar el prestamo,  porfavor intente de nuevo" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public List<PrestamoModel> ListaPrestamo(String texto) {

        List<PrestamoModel> lista = new ArrayList<>();

        String sql = "SELECT  \"id\",\"ejemplar_codigo\",\"usuario_id\",\"estado\", "
                + "\"fecha_prestamo\", \"fecha_limite\", \"fecha_devolucion\", "
                + "FROM \"prestamo\" "
                + "WHERE CAST(\"id\") AS TEXT) LIKE ? "
                + "OR CAST(\"ejemplar_codigo\" AS TEXT) LIKE ?"
                + "OR CAST(\"usuario_id\" AS TEXT) LIKE ? "
                + "OR LOWER(\"estado\") LIKE LOWER(?)";

        try (Connection conn = BDConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");
            ps.setString(3, "%" + texto + "%");
            ps.setString(4, "%" + texto + "%");
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int User=rs.getInt("usuario_id");
                CreateUserModel user=usuario.ObtenerUsuario(User);
                String book=rs.getString("ejemplar_codigo");
                BookManager libro=ejemplar.ObtenerEjemplar(book);
                PrestamoModel p = new PrestamoModel();

                p.setPrestamoCode(
                        rs.getInt("id"));

                p.setBookCode(libro);

                p.setUserCode(user);

               p.setState(
                        rs.getString("estado"));

                lista.add(p);
            }

        } catch (Exception e) {

            System.out.println("Error lista prestamos");
            e.printStackTrace();
        }

        return lista;
    }

    
    public PrestamoModel ObtenerPrestamo(int prestamoCode){
        String sql = "SELECT \"id\", \"ejemplar_codigo\", \"usuario_id\",\"estado\", \"fecha_prestamo\", \"fecha_limite\", \"fecha_devolucion\" "
                   + "FROM \"prestamo\" WHERE \"id\" = ?";
        
        try(Connection conn = BDConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
           ps.setInt(1, prestamoCode);
            try (ResultSet rs=ps.executeQuery()){
                if (rs.next()){
                    int User=rs.getInt("usuario_id");
                CreateUserModel user=usuario.ObtenerUsuario(User);
                String book=rs.getString("ejemplar_codigo");
                BookManager libro=ejemplar.ObtenerEjemplar(book);
                return new PrestamoModel(
                    rs.getInt("id"),
                    libro,
                    user,
                    rs.getString("estado"),
                    rs.getDate("fecha_prestamo"),
                    rs.getDate("fecha_limite"),
                    rs.getDate("fecha_devolucion")
                    
                           );
                    }
                }
 
        } catch (Exception e){
            System.out.println("Error de consulta del prestamo: "+ e.getMessage());
        }
        return null;
    }
}
