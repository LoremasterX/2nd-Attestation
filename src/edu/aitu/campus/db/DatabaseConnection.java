package edu.aitu.campus.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    private String url;
    private String user;
    private String password;

    private DatabaseConnection() {
        try {
            Properties props = new Properties();
            InputStream input =
                    getClass().getClassLoader().getResourceAsStream("db.properties");

            if (input == null) {
                throw new RuntimeException("db.properties not found");
            }

            props.load(input);

            this.url = props.getProperty("db.url");
            this.user = props.getProperty("db.user");
            this.password = props.getProperty("db.password");

        } catch (Exception e) {
            throw new RuntimeException("Failed to load database config", e);
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
