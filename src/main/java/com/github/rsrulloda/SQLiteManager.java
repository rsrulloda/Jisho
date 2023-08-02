package com.github.rsrulloda;

import java.nio.charset.StandardCharsets;
import java.sql.*;

public class SQLiteManager {
        private static final String DATABASE_URL = "jdbc:sqlite:C:\\Users\\ronel\\Documents\\Jisho\\src\\dictionary\\dictionary.db";
        private Connection connection;

        public SQLiteManager() {
            try {
                connection = DriverManager.getConnection(DATABASE_URL);
                System.out.println("Connected to SQLite database.");
            } catch (SQLException e) {
                System.err.println("Error connecting to SQLite database: " + e.getMessage());
            }
        }

    public void insertData(String column1Value, String column2Value, String column3Value, String column4Value, String column5Value) {
        try {
            String query = "INSERT INTO Dictionary (Japanese, Furigana, English, Class, Definition) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, column1Value);
            statement.setString(2, column2Value);
            statement.setString(3, column3Value);
            statement.setString(4, column4Value);
            statement.setString(5, column4Value);
            statement.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
        }
    }

    // Method to read data from the database based on a custom query
    public void readQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columns; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnName + ": " + columnValue + ", ");
                }
                System.out.println();
            }

            resultSet.close();
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        }
    }

    public String readQuery2(String query) {
        String s;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columns; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnValue = resultSet.getString(i);
                    return columnName + ": " + columnValue + ", ";
                }
            }

            resultSet.close();
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        }
        return "s";
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Disconnected from SQLite database.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
