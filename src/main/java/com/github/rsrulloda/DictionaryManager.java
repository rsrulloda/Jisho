package com.github.rsrulloda;

import java.sql.*;

public class DictionaryManager {
    private static final String DATABASE_URL = "jdbc:sqlite:C:\\Users\\ronel\\Documents\\Jisho\\src\\main\\resources\\dictionary.db";
    private Connection connection;


    public DictionaryManager() { // Constructor that opens connection to SQL database
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            System.out.println("Connected to SQLite database.");
        } catch (SQLException e) {
            System.err.println("Error connecting to SQLite database: " + e.getMessage());
        }
    }

    public Word getWotd() { // Gets current word of the day
        RandomIDGenerator randomIDGenerator = new RandomIDGenerator(getDatabaseSize());
        String query = "SELECT * FROM dictionary WHERE id = " + randomIDGenerator.getRandomID();
        System.out.println(randomIDGenerator.getRandomID());

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String japanese = resultSet.getString("japanese");
                String furigana = resultSet.getString("furigana");
                String english = resultSet.getString("english");
                String wordClass = resultSet.getString("class");

                return new Word(id, japanese, furigana, english, wordClass);
            } else {
                System.err.println("No rows found in the database.");
            }

            resultSet.close();
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        }

        return null; // Return null if there was an error or no rows found
    }

    public Word getRandomWord() {
        RandomIDGenerator randomIDGenerator = new RandomIDGenerator(getDatabaseSize());
        String query = "SELECT * FROM dictionary WHERE id = " + randomIDGenerator.generateNewRandomID();
        System.out.println(randomIDGenerator.getRandomID());

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String japanese = resultSet.getString("japanese");
                String furigana = resultSet.getString("furigana");
                String english = resultSet.getString("english");
                String wordClass = resultSet.getString("class");

                return new Word(id, japanese, furigana, english, wordClass);
            } else {
                System.err.println("No rows found in the database.");
            }

            resultSet.close();
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        }

        return null; // Return null if there was an error or no rows found
    }

    private int getDatabaseSize() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM dictionary");

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error getting row count: " + e.getMessage());
        }

        return 0;
    }

    public void closeConnection() { // Closes connection to SQL database
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
