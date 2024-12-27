package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.entity.User;

/**
 * UserDAO class for handling user-related database operations.
 * Provides methods for creating, retrieving, updating, and deleting users.
 */
public class UserDAO extends BaseDAO {

    /**
     * Maps a ResultSet row to a User object.
     * @param rs ResultSet containing the user data.
     * @return A User object with data from the current ResultSet row.
     * @throws SQLException if there is an issue extracting data.
     */
    private User mapRowToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),                // User ID
                rs.getString("user_name"),       // User name
                rs.getString("user_email"),      // User email
                rs.getString("hashed_password"), // Hashed password
                rs.getString("salt"),            // Salt used for hashing
                rs.getDate("create_date")        // User creation date
        );
    }

    /**
     * Creates a new user in the database.
     * @param user The User object containing user details to be inserted.
     * @return The created User object if the insertion was successful, otherwise an empty User object.
     * @throws Exception if there is an issue with database operations.
     */
    public User create(User user) throws Exception {
        String sql = "insert into users(user_name, user_email, hashed_password, salt, create_date, recent_access) "
                + "values(?, ?, ?, ?, ?, ?);";

        // Execute the update and return the user if rows were affected
        int rowsAffected = executeUpdate(
                sql,
                user.getName(),
                user.getEmail(),
                user.getHashedPassword(),
                user.getSalt(),
                user.getCreateDate(),
                user.getRecentAccess());

        return rowsAffected != 0 ? user : new User(); // Return the user if successful, else an empty user.
    }

    /**
     * Retrieves a user by ID from the database.
     * @param id The user ID to search for.
     * @return The User object if found, otherwise an empty User object.
     * @throws Exception if there is an issue with database operations.
     */
    public User retrieve(int id) throws Exception {
        String sql = "select * from users where id = ?;";
        ResultSet rs = null;

        try {
            rs = executeQuery(sql, id);  // Execute the query with the provided ID.
            if (rs.next()) {
                return mapRowToUser(rs); // If a user is found, return the mapped user.
            } else {
                return new User(); // Return an empty user if no result is found.
            }
        } finally {
            closeResource(null, rs); // Ensure resources are closed.
        }
    }

    /**
     * Retrieves a user by their username or email.
     * @param nameOrEmail The username or email to search for.
     * @return The User object if found, otherwise an empty User object.
     * @throws Exception if there is an issue with database operations.
     */
    public User retrieve(String nameOrEmail) throws Exception {
        String sql = "select * from users where user_name = ? or user_email = ?;";
        ResultSet rs = null;

        try {
            rs = executeQuery(sql, nameOrEmail, nameOrEmail); // Execute the query with the provided value.
            if (rs.next()) {
                return mapRowToUser(rs); // Return the mapped user if found.
            } else {
                return new User(); // Return an empty user if no result is found.
            }
        } finally {
            closeResource(null, rs); // Ensure resources are closed.
        }
    }

    /**
     * Retrieves a user based on any given field and value.
     * @param field The field name to search for (e.g., "user_name", "user_email").
     * @param value The value to search for in the specified field.
     * @return The User object if found, otherwise an empty User object.
     * @throws Exception if there is an issue with database operations.
     */
    public User retrieve(String field, String value) throws Exception {
        String sql = "select * from users where " + field + " = ?;";
        ResultSet rs = null;

        try {
            rs = executeQuery(sql, value); // Execute the query with the provided value.
            if (rs.next()) {
                return mapRowToUser(rs); // Return the mapped user if found.
            } else {
                return new User(); // Return an empty user if no result is found.
            }
        } finally {
            closeResource(null, rs); // Ensure resources are closed.
        }
    }

    /**
     * Retrieves the user ID based on any given field and value.
     * @param field The field name to search for (e.g., "user_name", "user_email").
     * @param value The value to search for in the specified field.
     * @return The user ID if the user is found, otherwise 0.
     * @throws Exception if there is an issue with database operations.
     */
    public int retrieveId(String field, String value) throws Exception {
        User user = retrieve(field, value); // Retrieve user by field and value.
        return user.getId(); // Return the user ID (0 if not found).
    }

    /**
     * Updates an existing user's details in the database.
     * @param user The User object containing updated user details.
     * @return The updated User object if the update was successful, otherwise an empty User object.
     * @throws Exception if there is an issue with database operations.
     */
    public User update(User user) throws Exception {
        String sql = "update users set "
                + "user_name = ?, "
                + "user_email = ?, "
                + "hashed_password = ?, "
                + "salt = ?, "
                + "recent_access = ? "
                + "where id = ?;";

        // Execute the update and return the user if rows were affected
        int rowsAffected = executeUpdate(
                sql,
                user.getName(),
                user.getEmail(),
                user.getHashedPassword(),
                user.getSalt(),
                user.getRecentAccess(),
                user.getId());

        return rowsAffected != 0 ? user : new User(); // Return the updated user if successful, else an empty user.
    }

    /**
     * Deletes a user from the database.
     * @param user The User object to be deleted.
     * @return The deleted User object if the deletion was successful, otherwise an empty User object.
     * @throws Exception if there is an issue with database operations.
     */
    public User delete(User user) throws Exception {
        String sql = "delete from users where id = ?;";

        // Execute the deletion and return the user if rows were affected
        int rowsAffected = executeUpdate(sql, user.getId());

        return rowsAffected != 0 ? user : new User(); // Return the deleted user if successful, else an empty user.
    }

}
