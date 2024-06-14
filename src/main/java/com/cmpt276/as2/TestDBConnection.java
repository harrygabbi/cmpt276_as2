package com.cmpt276.as2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://dpg-cphoimuct0pc73fheud0-a.oregon-postgres.render.com:5432/cmpt276_as2";
        String user = "cmpt276_as2_user";
        String password = "m2ezU7Bq6yknfqR35Ns6hIIe74UhonHj";
        
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
