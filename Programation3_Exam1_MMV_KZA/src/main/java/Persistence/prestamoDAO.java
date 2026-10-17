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

        String sql = "INSERT INTO prestamo (ejemplar_codigo, usuario_id, estado, fecha_prestamo, fecha_limite, fecha_devolucion) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = BDConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            
            ps.setString(1, p.getBookCode().getCodigoEjemplar());
            ps.setInt(2, p.getUserCode().getIdentification());
            ps.setString(3, p.getState());
            ps.setDate(4, p.getPrestamoDate());
            ps.setDate(5, p.getLimitDate());
            ps.setDate(6, p.getReturnDate());
            

            int result = ps.executeUpdate();

            if(result > 0){
                
                System.out.println("Loan Successfully Added");
                
                if(p.getState().equalsIgnoreCase("Activo")){
                
                    String sqlEjemplar = "UPDATE ejemplar SET estado = 'Prestado' WHERE codigo = ?";
                    
                    try(PreparedStatement ps2 = conn.prepareStatement(sqlEjemplar)){
                    
                        ps2.setString(1, p.getBookCode().getCodigoEjemplar());
                        ps2.executeUpdate();
                    }
                }
            }
            return result > 0;

        } catch (Exception e) {

            System.out.println("Error al guardar el prestamo");
            e.printStackTrace();

            return false;
        }
    }
    public boolean BorrarPrestamo(int prestamoCode)  {
        
        String sql = "DELETE FROM prestamo WHERE id = ?";
        
        try (Connection conn = BDConnection.conectar();
              PreparedStatement ps = conn.prepareStatement(sql)){               
                                          
            ps.setInt(1, prestamoCode);
            return ps.executeUpdate() > 0;
        }
         catch(Exception e){
            System.out.println("Error al intentar borrar el prestamo" + e.getMessage());
            e.printStackTrace();
            return false;
         }
        
    }
    
    public boolean ModificarPrestamo(PrestamoModel p){

    String sql = "UPDATE prestamo SET ejemplar_codigo = ?, "
            + "usuario_id = ?, estado = ?, fecha_prestamo = ?, "
            + "fecha_limite = ?, fecha_devolucion = ? WHERE id = ?";

    try (Connection conn = BDConnection.conectar();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, p.getBookCode().getCodigoEjemplar());
        ps.setInt(2, p.getUserCode().getIdentification());
        ps.setString(3, p.getState());
        ps.setDate(4, p.getPrestamoDate());
        ps.setDate(5, p.getLimitDate());
        ps.setDate(6, p.getReturnDate());
        ps.setInt(7, p.getPrestamoCode());

        return ps.executeUpdate() > 0;
        
        }catch(SQLException e){
            System.out.println("Ocurrio un error al actualizar el ejemplar,  porfavor intente de nuevo" + e.getMessage());
            e.printStackTrace();
            return false;
    }
}
    
    public List<PrestamoModel> ListaPrestamo(String texto) {

        List<PrestamoModel> lista = new ArrayList<>();

        String sql = "SELECT id, ejemplar_codigo, usuario_id, estado, fecha_prestamo, fecha_limite, fecha_devolucion FROM prestamo WHERE"
                + " CAST(id AS CHAR) LIKE ? OR CAST(ejemplar_codigo AS CHAR) LIKE ? OR CAST(usuario_id AS CHAR) LIKE ? OR LOWER(estado) LIKE LOWER(?)";

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

                p.setPrestamoCode(rs.getInt("id"));
                p.setBookCode(libro);
                p.setUserCode(user);
                p.setState(rs.getString("estado"));
                p.setPrestamoDate(rs.getDate("fecha_prestamo"));
                p.setLimitDate(rs.getDate("fecha_limite"));
                p.setReturnDate(rs.getDate("fecha_devolucion"));

                lista.add(p);
            }

        } catch (Exception e) {

            System.out.println("Error lista prestamos");
            e.printStackTrace();
        }

        return lista;
    }
    
    public PrestamoModel ObtenerPrestamo(int prestamoCode){
        String sql = "SELECT id, ejemplar_codigo, usuario_id, estado, fecha_prestamo, fecha_limite, fecha_devolucion FROM prestamo WHERE id = ?";
        
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
                        rs.getDate("fecha_devolucion"));
                    }
                }
 
        } catch (Exception e){
            System.out.println("Error de consulta del prestamo: "+ e.getMessage());
        }
        return null;
    }
}