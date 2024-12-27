package service.dao;

import model.entity.User;
import model.entity.Result;
import exceptions.BusinessException;
import exceptions.DataAccessException;
import java.sql.Connection;
import java.sql.SQLException;
import utils.databaseUtils.ConnectionManager;
import utils.resultUtils.AuthResult;

/**
 * This is an abstract base class providing common functionality for database
 * service operations. It manages database connections and transactions,
 * including begin, commit, and rollback operations. Derived classes can use
 * these methods to execute database operations while ensuring proper
 * transaction handling.
 */
public abstract class BaseService {

    // Thread-local connection to ensure that each thread has its own connection instance.
    protected static ThreadLocal<Connection> threadConnection = new ThreadLocal<>();

    /**
     * Retrieves the current thread's database connection, or creates one if
     * none exists.
     *
     * @return The database connection associated with the current thread.
     * @throws SQLException If there is an error while retrieving or creating
     * the connection.
     */
    protected Connection getConnection() throws SQLException {
        // If no connection is associated with the current thread, create one and set auto-commit to false.
        if (threadConnection.get() == null) {
            Connection con = ConnectionManager.getConnection();
            con.setAutoCommit(false);
            threadConnection.set(con);
        }
        return threadConnection.get();
    }

    /**
     * Starts a transaction by getting the connection and setting auto-commit to
     * false.
     *
     * @throws SQLException If there is an error while starting the transaction.
     */
    protected void beginTransaction() throws SQLException {
        getConnection();
    }

    /**
     * Commits the current transaction.
     *
     * @throws SQLException If there is an error while committing the
     * transaction.
     */
    protected void commitTransaction() throws SQLException {
        Connection con = threadConnection.get();
        if (con != null) {
            con.commit(); // Commit the transaction.
        }
    }

    /**
     * Rolls back the current transaction if an error occurs.
     *
     * @throws SQLException If there is an error while rolling back the
     * transaction.
     */
    protected void rollbackTransaction() {
        Connection con = threadConnection.get();
        try {
            if (con != null) {
                con.rollback(); // Rollback the transaction to ensure consistency.
            }
        } catch (SQLException e) {
            // Handle the error (e.g., logging it) if rollback fails.
        }
    }

    /**
     * Closes the current database connection and removes it from the
     * thread-local storage.
     */
    protected void closeConnection() {
        Connection con = threadConnection.get();
        try {
            if (con != null) {
                con.close(); // Close the connection.
            }
        } catch (SQLException e) {
            // Handle the error (e.g., logging it) if closing the connection fails.
        } finally {
            threadConnection.remove(); // Ensure the connection is removed from thread-local storage.
        }
    }

    /**
     * Retrieves the thread-local database connection.
     *
     * @return The thread-local connection instance, which is used to ensure
     * each thread has its own connection.
     */
    public static ThreadLocal<Connection> getThreadConnection() {
        return threadConnection;
    }

    /**
     * Executes a database service operation and handles transactions and
     * errors.
     *
     * @param operation The service operation to execute (wrapped as a
     * functional interface).
     * @param <T> The type of result returned by the operation.
     * @return The result of the operation, including success or failure
     * information.
     */
    protected <T> Result<T> executeServiceOperation(ServiceOperation<T> operation) {
        try {
            beginTransaction(); // Start a transaction.
            T result = operation.execute(); // Execute the service operation.
            commitTransaction(); // Commit the transaction if the operation is successful.
            return AuthResult.success(result, "success"); // Return a success result.
        } catch (BusinessException e) {
            rollbackTransaction(); // Rollback if there is a business exception.
            return AuthResult.fail(new User(), "Invalid data", e.getMessage()); // Return a failure result.
        } catch (DataAccessException e) {
            rollbackTransaction(); // Rollback if there is a data access exception.
            return AuthResult.error(null, "A database error occurred.", e.getCause().getMessage()); // Return an error result.
        } catch (Exception e) {
            rollbackTransaction(); // Rollback for any other unexpected exceptions.
            return AuthResult.error(null, "An unexpected error occurred.", e.getMessage()); // Return an error result.
        } finally {
            closeConnection(); // Ensure the connection is closed after the operation.
        }
    }

    /**
     * A functional interface representing a service operation that can throw
     * exceptions. This interface allows us to pass any operation that will be
     * executed within the context of a database transaction.
     *
     * @param <T> The type of the result returned by the operation.
     */
    @FunctionalInterface
    public interface ServiceOperation<T> {

        /**
         * Executes the operation.
         *
         * @return The result of the operation.
         * @throws Exception If an error occurs during the operation.
         */
        T execute() throws Exception;
    }

}
