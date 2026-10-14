
package Persistence;

import DataBaseConnection.BDConnection;
import Model.AddBookModel;
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
public class libroDAO {
    
    public void AgregarLibro(AddBookModel book) {//Intenta Agregrar 2 Veces un mismo libro

        String sql = "INSERT INTO libro (isbn, titulo, autor, anio) VALUES (?, ?, ?, ?)";

        try (Connection conn = BDConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getbookCode());
            ps.setString(2, book.getbookName());
            ps.setString(3, book.getbookAuthor());
            ps.setInt(4, book.getbookYear());
            
            int result = ps.executeUpdate();
            
            if(result > 0){System.out.println("Book Successfully Added");}

            //return true;

        } catch (Exception e) {

            System.out.println("Error al guardar el libro");
            e.printStackTrace();

            //return false;
        }
    }
    public void BorrarLibro(String bookCode) throws SQLException {
        
        String sql = "DELETE FROM libro WHERE isbn = ?";
        
        try (Connection conn = BDConnection.conectar();
              PreparedStatement ps = conn.prepareStatement(sql)){               
                                          
            ps.setString(1, bookCode);
            int filas = ps.executeUpdate();
            
            if(filas > 0){System.out.println("Book Successfully deleted");}
            //return filas > 0;
            
        }
         catch(Exception e){
            System.out.println("Error al intentar borrar el libro" + e.getMessage());
            e.printStackTrace();
             //return false;
         }
        
    }
    
    public void ModificarLibro(AddBookModel book) throws SQLException{
        
        String sql = "UPDATE libro SET titulo = ?, autor = ?, anio = ? WHERE isbn = ?";
        
        try(Connection conn = BDConnection.conectar();
                
           PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, book.getbookName());
            ps.setString(2, book.getbookAuthor());
            ps.setInt(3, book.getbookYear());
           
            
            int filas = ps.executeUpdate();
            
            if(filas > 0){System.out.println("Book Successfully Updated");}
            
            //return filas > 0;
              
           }catch(SQLException e){
            System.out.println("Ocurrio un error al actualizar el libro,  porfavor intente de nuevo" + e.getMessage());
            e.printStackTrace();
            //return false;
        }
    }
    
    public List<AddBookModel> ListaLibro(String texto) {

        List<AddBookModel> lista = new ArrayList<>();

        String sql = "SELECT * FROM libro WHERE LOWER(titulo) LIKE LOWER(?)";

        try (Connection conn = BDConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + texto + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                AddBookModel book = new AddBookModel();

                book.setbookCode(rs.getString("isbn"));
                book.setbookName(rs.getString("titulo"));
                book.setbookAuthor(rs.getString("autor"));
                book.setbookYear(rs.getInt("anio"));

                lista.add(book);
            }

        } catch (Exception e) {

            System.out.println("Error lista libro");
            e.printStackTrace();
        }

        return lista;
    }

    
    public AddBookModel ObtenerLibro(String bookCode){
        
        String sql = "SELECT isbn, titulo, autor, anio FROM libro WHERE isbn = ?";
        
        try(Connection conn = BDConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
           ps.setString(1, bookCode);
           
            try (ResultSet rs=ps.executeQuery()){
                
                if (rs.next()){
                    
                return new AddBookModel(rs.getString("isbn"), rs.getString("titulo"),rs.getString("autor"),rs.getInt("anio"));}
                }
 
        } catch (Exception e){System.out.println("Error de consulta del libro: "+ e.getMessage());}
        
        return null;
    }
    
}
