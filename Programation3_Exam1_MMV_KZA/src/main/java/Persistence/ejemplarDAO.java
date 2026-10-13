package Persistence;

import DataBaseConnection.BDConnection;
import Model.AddBookModel;
import Model.BookManager;
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
public class ejemplarDAO {
    
    LibroDAO books = new LibroDAO();
    
    public void AgregarEjemplar(BookManager ejemplar) {

        String sql = "INSERT INTO ejemplar (codigo, libro_isbn, estado) VALUES (?, ?, ?)";

        try (Connection conn = BDConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ejemplar.getCodigoEjemplar());
            ps.setString(2, ejemplar.getLibro().getbookCode());
            ps.setString(3, ejemplar.getEstado());

            int result = ps.executeUpdate();

            if(result > 0){System.out.println("Example Successfully added.");}
            //return true;

        } catch (Exception e) {

            System.out.println("Error al guardar el Ejemplar");
            e.printStackTrace();

            //return false;
        }
    }
    public void BorrarEjemplar(String Code) throws SQLException {
        
        String sql = "DELETE FROM ejemplar WHERE codigo = ?";
        
        try (Connection conn = BDConnection.conectar();
              PreparedStatement ps = conn.prepareStatement(sql)){               
                                          
            ps.setString(1, Code);
            int filas = ps.executeUpdate();
            
            if(filas > 0){System.out.println("Example Successfully deleted.");}
            //return filas > 0;
            
        }
         catch(Exception e){
            System.out.println("Error al intentar borrar el ejemplar" + e.getMessage());
            e.printStackTrace();
            //return false;
         }
        
    }
    
    public void ModificarEjemplar(BookManager ejemplar) throws SQLException{
        
        String sql = "UPDATE ejemplar SET libro_isbn = ?, estado = ? WHERE codigo = ?";
        
        try(Connection conn = BDConnection.conectar();
                
           PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, ejemplar.getCodigoEjemplar());
            ps.setString(2, ejemplar.getLibro().getbookCode());
            ps.setString(3, ejemplar.getEstado());
           
            
            int filas = ps.executeUpdate();
            
            if(filas > 0){System.out.println("Example Successfully Updated.");}
            //return filas > 0;
              
           }catch(SQLException e){
            System.out.println("Ocurrio un error al actualizar el ejemplar,  porfavor intente de nuevo" + e.getMessage());
            e.printStackTrace();
            //return false;
        }
    }
    
    public List<BookManager> ListaEjemplar(String texto) {

        List<BookManager> lista = new ArrayList<>();

        String sql = "SELECT * FROM ejemplar WHERE LOWER(codigo) LIKE LOWER(?)";

        try (Connection conn = BDConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + texto + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                String bookm=rs.getString("libro_isbn");
                AddBookModel libro=books.ObtenerLibro(bookm);
                BookManager book = new BookManager();

                book.setCodigoEjemplar(rs.getString("codigo"));
                book.setLibro(libro);
                book.setEstado(rs.getString("estado"));
                
                lista.add(book);
            }

        } catch (Exception e) {

            System.out.println("Error lista de Ejemplares");
            e.printStackTrace();
        }

        return lista;
    }

    
    public BookManager ObtenerEjemplar(String Code){
        
        String sql = "SELECT codigo, libro_isbn, estado FROM ejemplar WHERE codigo = ?";
        
        try(Connection conn = BDConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
           ps.setString(1, Code);
           
            try (ResultSet rs=ps.executeQuery()){
                
                if (rs.next()){
                    
                    String book=rs.getString("libro_isbn");
                    AddBookModel libro=books.ObtenerLibro(book);
                    
                    return new BookManager(
                    rs.getString("codigo"),
                    libro,
                    rs.getString("estado"));
                    }
                }
 
        } catch (Exception e){System.out.println("Error de consulta del Ejemplar: "+ e.getMessage());}
        return null;
    }
    
}
