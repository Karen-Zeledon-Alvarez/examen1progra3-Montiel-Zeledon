package DataBaseConnection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BDConnection {

    public static String URL = "jdbc:mysql://mysql.us.cloudlogin.co:3306/gamabasis_ex1p3g7?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    public static String user = "gamabasis_ex1p3g7";
    public static String password = "Z8h0KB5km(";

    public Connection connection_;
    public PreparedStatement ps;
    public ResultSet rs;

    private static Connection connection2;
    private static long lastUse = 0;
    public static synchronized Connection conectar() throws SQLException {

        long ahora = System.currentTimeMillis();

        boolean signal = connection2 == null || connection2.isClosed() || (ahora - lastUse > 30_000 && !connection2.isValid(2));

        if (signal) {connection2 = DriverManager.getConnection(URL, user, password);}
        lastUse = ahora;

        final Connection real = connection2;

        return (Connection) Proxy.newProxyInstance(
            BDConnection.class.getClassLoader(),
            new Class<?>[]{Connection.class},
            (proxy, method, args) -> {
                if (method.getName().equals("close")) {return null;}
                try {return method.invoke(real, args);}
                catch (InvocationTargetException ex) {throw ex.getCause();}
        });
    }

    public BDConnection() {
        Connection _connection = getConnection();
    }

    public Connection getConnection() {

        connection_ = null;

        try {
            connection_ = DriverManager.getConnection(URL, user, password);
            System.out.println("Successful Connection");

        } catch (Exception ex) {System.err.println("Error " + ex);}

        return connection_;
    }
}