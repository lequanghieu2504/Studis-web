package utils.databaseUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is responsible for managing database connections.
 * It provides a method to establish a connection to the PostgreSQL database.
 */
public class ConnectionManager {

    /**
     * Establishes a connection to the PostgreSQL database.
     * 
     * @return A Connection object representing the connection to the database, or null if the connection fails.
     */
    public static Connection getConnection() {
        // URL of the PostgreSQL database, including the user credentials
        String url = "jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres?user=postgres.sudvsxblwhrvygkdlxio&password=password";
        
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            
            // Return a new database connection using the provided URL
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            // Return null if there is an error establishing the connection
            return null;
        }
    }
}
