/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.daoService.BaseService;
import utils.databaseUtils.ConnectionManager;

/**
 *
 * @author ho huy
 */
public abstract class BaseDAO {

    private static final Logger logger = Logger.getLogger(BaseDAO.class.getName());
    private static ThreadLocal<Connection> threadConnection = BaseService.getThreadConnection();

    protected Connection getConnection() throws Exception {
        if (threadConnection.get() == null) {
            Connection con = ConnectionManager.getConnection();
            con.setAutoCommit(false);
            threadConnection.set(con);
        }
        return threadConnection.get();
    }

    protected void closeResource(PreparedStatement stmt, ResultSet rs) {
        try {
            if (stmt != null) {
                stmt.close();
            }

            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to close PreparedStatement or ResultSet", e);
            System.err.println("Error occurred while closing resources: " + e.getMessage());
        }
    }

    protected int executeUpdate(String sql, Object... params) throws SQLException, Exception {
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            setParameters(stmt, params);
            return stmt.executeUpdate();
        }
    }

    protected ResultSet executeQuery(String sql, Object... params) throws SQLException, Exception {
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        setParameters(stmt, params);
        return stmt.executeQuery();
    }

    private void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }

}
