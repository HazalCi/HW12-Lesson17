package org.example.databases.JDBC;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/mydatabase2";
        String USER = "root";
        String PASSWORD = "password";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            DatabaseOperations.createTable(connection);
            DatabaseOperations.insertData(connection);
            DatabaseOperations.readData(connection);
            DatabaseOperations.deleteData(connection);
            DatabaseOperations.readData(connection);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
