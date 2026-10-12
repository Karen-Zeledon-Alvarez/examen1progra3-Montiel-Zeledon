/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;
import Model.CreateUserModel;
import DataBaseConnection.BDConnection;
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
public class usuarioDAO {
    
    public boolean AgregarUsuario(CreateUserModel user) {

        String sql = "INSERT INTO \"usuario\" "
                + "(\"identificacion\", \"nombre\", "
                + "\"correo\", \"activo\") "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = BDConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setInt(2, user.getIdentification());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getStatus());
            

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println("Error al guardar usuario");
            e.printStackTrace();

            return false;
        }
    }
    public boolean BorrarUsuario(int identificacion) throws SQLException {
        
        String sql = "DELETE FROM \"usuario\" WHERE \"identificacion\" = ?";
        
        try (Connection conn = BDConnection.conectar();
              PreparedStatement ps = conn.prepareStatement(sql)){               
                                          
            ps.setInt(1, identificacion);
            int filas = ps.executeUpdate();
            return filas > 0;
            
        }
         catch(Exception e){
            System.out.println("Error al intentar borrar el usuario" + e.getMessage());
            e.printStackTrace();
             return false;
         }
        
    }
    
    public boolean ModificarUsuario(CreateUserModel user) throws SQLException{
        
        String sql = "UPDATE \"usuario\" SET \"nombre\" = ?, \"correo\" = ?, \"activo\" = ? "
                   + "WHERE \"identificacion\" = ?";
        
        try(Connection conn = BDConnection.conectar();
                
           PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getStatus());
           
            
            int filas = ps.executeUpdate();
            return filas > 0;
              
           }catch(SQLException e){
            System.out.println("Ocurrio un error al actualizar el usuario,  porfavor intente de nuevo" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public List<CreateUserModel> ListaUsuario(String texto) {

        List<CreateUserModel> lista = new ArrayList<>();

        String sql = "SELECT * FROM \"usuario\" "
                + "WHERE LOWER(\"nombre\") "
                + "LIKE LOWER(?)";

        try (Connection conn = BDConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + texto + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CreateUserModel user = new CreateUserModel();

                user.setIdentification(
                        rs.getInt("identificacion"));

                user.setUsername(
                        rs.getString("nombre"));

                user.setEmail(
                        rs.getString("correo"));

                user.setStatus(
                        rs.getString("activo"));

                lista.add(user);
            }

        } catch (Exception e) {

            System.out.println("Error lista Usuario");
            e.printStackTrace();
        }

        return lista;
    }

    
    public CreateUserModel ObtenerUsuario(int identificacion){
        String sql = "SELECT \"identificacion\", \"nombre\", \"correo\", \"activo\" "
                   + "FROM \"usuario\" WHERE \"identificacion\" = ?";
        
        try(Connection conn = BDConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
           ps.setInt(1, identificacion);
            try (ResultSet rs=ps.executeQuery()){
                if (rs.next()){
                return new CreateUserModel(
                    rs.getInt("identificacion"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("activo")
                    
                           );
                    }
                }
 
        } catch (Exception e){
            System.out.println("Error de consulta de la Usuario: "+ e.getMessage());
        }
        return null;
    }
    
    
}
