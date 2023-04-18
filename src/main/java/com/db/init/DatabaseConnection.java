package com.db.init;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String SETTINGS_FILE = "settings.properties";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                Properties prop = readSettings();
                String driver = prop.getProperty("db.driver");
                String url = prop.getProperty("db.url");
                String username = prop.getProperty("db.username");
                String password = prop.getProperty("db.password");

                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Failed to connect to database.");
                e.printStackTrace();
            }
        }

        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to close database connection.");
            e.printStackTrace();
        }
    }

    public static Properties readSettings() throws IOException {
        Properties properties = new Properties();
        try(InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("settings.properties")) {
            properties.load(inputStream);
        }
        return properties;
    }



}
