package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.dao.BaseService;
import utils.databaseUtils.ConnectionManager;

/**
 * BaseDAO class that provides common database operations such as
 * executing queries and updates, and handling database connections.
 */
public abstract class BaseDAO {

    private static final Logger logger = Logger.getLogger(BaseDAO.class.getName()); // Logger for error handling.
    private static ThreadLocal<Connection> threadConnection = BaseService.getThreadConnection(); // Manages database connections per thread.

    /**
     * Retrieves the database connection for the current thread.
     * @return Connection object for the current thread.
     * @throws Exception if the connection cannot be established.
     */
    protected Connection getConnection() throws Exception {
        if (threadConnection.get() == null) {
            // If no connection exists for the current thread, create a new one.
            Connection con = ConnectionManager.getConnection();
            con.setAutoCommit(false); // Disable auto-commit for transaction control.
            threadConnection.set(con); // Store the connection in the thread-local variable.
        }
        return threadConnection.get(); // Return the connection for the current thread.
    }

    /**
     * Closes the database resources such as PreparedStatement and ResultSet.
     * @param stmt PreparedStatement to close.
     * @param rs ResultSet to close.
     */
    protected void closeResource(PreparedStatement stmt, ResultSet rs) {
        try {
            if (stmt != null) {
                stmt.close(); // Close the PreparedStatement.
            }

            if (rs != null) {
                rs.close(); // Close the ResultSet.
            }
        } catch (SQLException e) {
            // Log and print error if closing resources fails.
            logger.log(Level.SEVERE, "Failed to close PreparedStatement or ResultSet", e);
            System.err.println("Error occurred while closing resources: " + e.getMessage());
        }
    }

    /**
     * Executes an update query (e.g., INSERT, UPDATE, DELETE) with the given SQL and parameters.
     * @param sql SQL query to execute.
     * @param params Parameters to set in the PreparedStatement.
     * @return The number of affected rows.
     * @throws SQLException if the query fails.
     * @throws Exception if there is an issue with the database connection.
     */
    protected int executeUpdate(String sql, Object... params) throws SQLException, Exception {
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            setParameters(stmt, params); // Set parameters for the PreparedStatement.
            return stmt.executeUpdate(); // Execute the update and return the number of affected rows.
        }
    }

    /**
     * Executes a query (e.g., SELECT) and returns the result set.
     * @param sql SQL query to execute.
     * @param params Parameters to set in the PreparedStatement.
     * @return ResultSet containing the results of the query.
     * @throws SQLException if the query fails.
     * @throws Exception if there is an issue with the database connection.
     */
    protected ResultSet executeQuery(String sql, Object... params) throws SQLException, Exception {
        PreparedStatement stmt = getConnection().prepareStatement(sql); // Prepare the SQL query.
        setParameters(stmt, params); // Set parameters for the PreparedStatement.
        return stmt.executeQuery(); // Execute the query and return the ResultSet.
    }

    /**
     * Sets the parameters for the PreparedStatement.
     * @param stmt PreparedStatement to set parameters for.
     * @param params Parameters to set in the PreparedStatement.
     * @throws SQLException if setting parameters fails.
     */
    private void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]); // Set each parameter in the PreparedStatement.
        }
    }
}
