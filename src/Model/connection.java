package Model;
import java.sql.*;

public class connection {
    private static Connection con = null;

    public static Connection getConnection() {
        if (con == null) {  // Ensure only one connection is created
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");  // Load MySQL Driver
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafe", "root", "2433");
                System.out.println("Database Connected Successfully!");
            } catch (ClassNotFoundException e) {
                System.out.println("MySQL JDBC Driver not found.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Connection failed! Check database settings.");
                e.printStackTrace();
            }
        }
        return con;
    }
    
    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.out.println("Error while closing connection.");
                e.printStackTrace();
            }
        }
    }
}
