/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

/**
 *
 * @author ho huy
 */
public class UserDAO extends BaseDAO {

    private User mapRowToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("user_name"),
                rs.getString("user_email"),
                rs.getString("hashed_password"),
                rs.getString("salt"),
                rs.getDate("create_date"));
    }

    public User create(User user) throws Exception {
        String sql = "insert into users(user_name, user_email, hashed_password, salt, create_date, recent_access) "
                + "values(?, ?, ?, ?, ?, ?);";

        int rowsAffected = executeUpdate(
                sql,
                user.getName(),
                user.getEmail(),
                user.getHashedPassword(),
                user.getSalt(),
                user.getCreateDate(),
                user.getRecentAccess());

        return rowsAffected != 0 ? user : new User();
    }

    public User retrieve(int id) throws Exception {
        String sql = "select * from users where id = ?;";
        ResultSet rs = null;

        try {
            rs = executeQuery(sql, id);
            if (rs.next()) {
                return mapRowToUser(rs);
            } else {
                return new User();
            }
        } finally {
            closeResource(null, rs);
        }
    }

    public User retrieve(String nameOrEmail) throws Exception {
        String sql = "select * from users where user_name = ? or user_email = ?;";
        ResultSet rs = null;

        try {
            rs = executeQuery(sql, nameOrEmail, nameOrEmail);
            if (rs.next()) {
                return mapRowToUser(rs);
            } else {
                return new User();
            }
        } finally {
            closeResource(null, rs);
        }
    }

    public User retrieve(String field, String value) throws Exception {
        String sql = "select * from users where " + field + " = ?;";
        ResultSet rs = null;

        try {
            rs = executeQuery(sql, value);
            if (rs.next()) {
                return mapRowToUser(rs);
            } else {
                return new User();
            }
        } finally {
            closeResource(null, rs);
        }
    }

    public int retrieveId(String field, String value) throws Exception{
        User user = retrieve(field, value);
        return user.getId();
    }

    public User update(User user) throws Exception {
        String sql = "update users set "
                + "user_name = ?, "
                + "user_email = ?, "
                + "hashed_password = ?, "
                + "salt = ?, "
                + "recent_access = ? "
                + "where id = ?;";

        int rowsAffected = executeUpdate(
                sql,
                user.getName(),
                user.getEmail(),
                user.getHashedPassword(),
                user.getSalt(),
                user.getRecentAccess(),
                user.getId());

        return rowsAffected != 0 ? user : new User();

    }

    public User delete(User user) throws Exception {
        String sql = "delete from users where id = ?;";

        int rowsAffected = executeUpdate(sql, user.getId());

        return rowsAffected != 0 ? user : new User();
    }

}
