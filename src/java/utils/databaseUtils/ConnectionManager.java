/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.databaseUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ho huy
 */
public class ConnectionManager {

    public static Connection getConnection() {
        String url = "jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres?user=postgres.sudvsxblwhrvygkdlxio&password=password";
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            //do something to alert error
            return null;
        }
    }

}
