package edu.aitu.campus.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    private static final String URL =
            "jdbc:postgresql://aws-1-ap-south-1.pooler.supabase.com:5432/postgres?sslmode=require";

    private static final String USER =
            "postgres.mldyofrrtrljlvqnuexv";

    private static final String PASSWORD =
            "YOUR_PASSWORD_HERE";

    // private constructor k Singletonu
    private DatabaseConnection() {
    }

    // global access point
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // get JDBC connection
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
