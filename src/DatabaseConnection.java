import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.Class;

public class DatabaseConnection {

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        } catch (ClassNotFoundException ce) {
            System.out.println("Exception in which SQL Server Driver Isn't Found");
            ce.printStackTrace();
            return null;
        }

        Connection connection = null;

        try {
            String url = "ENTER_YOUR_SERVER_URL";


            connection = DriverManager.getConnection(url, "ENTER_YOUR_USERNAME", "ENTER_YOUR_PASSWORD");

        } catch (SQLException se) {
            System.out.println("Connection To Database Failed");
            se.printStackTrace();
            return null;
        }

        return connection;
    }
}
