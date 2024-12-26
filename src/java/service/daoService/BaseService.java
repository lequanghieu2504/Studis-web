/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.daoService;

import exceptions.BusinessException;
import exceptions.DataAccessException;
import java.sql.Connection;
import java.sql.SQLException;
import model.Result;
import utils.databaseUtils.ConnectionManager;

/**
 *
 * @author ho huy
 */
public abstract class BaseService {

    protected static ThreadLocal<Connection> threadConnection = new ThreadLocal<>();

    protected Connection getConnection() throws SQLException {
        if (threadConnection.get() == null) {
            Connection con = ConnectionManager.getConnection();
            con.setAutoCommit(false);
            threadConnection.set(con);
        }
        return threadConnection.get();
    }

    protected void beginTransaction() throws SQLException {
        getConnection();
    }

    protected void commitTransaction() throws SQLException {
        Connection con = threadConnection.get();
        if (con != null) {
            con.commit();
        }
    }

    protected void rollbackTransaction() {
        Connection con = threadConnection.get();
        try {
            if (con != null) {
                con.rollback();
            }
        } catch (SQLException e) {
            //do something to alert error
        }
    }

    protected void closeConnection() {
        Connection con = threadConnection.get();
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            //do something to alert error
        } finally {
            threadConnection.remove();
        }
    }

    public static ThreadLocal<Connection> getThreadConnection() {
        return threadConnection;
    }

    protected <T> Result<T> executeServiceOperation(ServiceOperation<T> operation) {
        try {
            beginTransaction();
            T result = operation.execute();
            commitTransaction();
            return new Result<>(200, "Success", result, null);
        } catch (BusinessException e) {
            rollbackTransaction();
            return new Result<>(400, e.getMessage(), null, "Invalid data.");
        } catch (DataAccessException e) {
            rollbackTransaction();
            return new Result<>(500, "A database error occurred.", null, e.getCause().getMessage());
        } catch (Exception e) {
            rollbackTransaction();
            return new Result<>(500, "An unexpected error occurred.", null, e.getMessage());
        } finally {
            closeConnection();
        }
    }

    @FunctionalInterface
    public interface ServiceOperation<T> {

        T execute() throws Exception;
    }

}
