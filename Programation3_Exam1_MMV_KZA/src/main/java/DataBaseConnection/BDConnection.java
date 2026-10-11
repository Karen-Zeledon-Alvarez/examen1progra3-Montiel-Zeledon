package DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class BDConnection {
         
    public static String URL = "jdbc:mysql://mysql.us.cloudlogin.co:3306/gamabasis_ex1p3g7?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    public static String user = "gamabasis_ex1p3g7";
    public static String password = "Z8h0KB5km(";

    public Connection connection_;
    public PreparedStatement ps;
    public ResultSet rs;
    
    public BDConnection(){Connection _connection = getConnection();}
    
    public Connection getConnection(){
    
        connection_ = null;
        
        try{
            connection_ = DriverManager.getConnection(URL, user, password);
            System.out.println("Successful Connection");
            
        }catch(Exception ex){System.err.println("Error " + ex);}
        
        return connection_;
    }
}