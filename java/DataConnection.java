import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
    
    public static Connection getDBConnection() throws SQLException {
       
        String url = "jdbc:postgresql://localhost:5432/product_management_db";
        String user = "product_manager_user";
        String password = "123456";
        
        return DriverManager.getConnection(url, user, password);
    }
}