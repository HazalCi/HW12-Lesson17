package org.example.databases.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DatabaseOperations {
    public static void createTable(Connection connection) throws SQLException {
        String createSQL = "CREATE TABLE IF NOT EXISTS ad_soyad (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "first_name VARCHAR(50) NOT NULL," +
                "last_name VARCHAR(50) NOT NULL," +
                "position VARCHAR(50)" +
                ")";
        try (PreparedStatement createStatement = connection.prepareStatement(createSQL)) {
            createStatement.executeUpdate();
            System.out.println("Tablo oluşturuldu: ad_soyad");
        }
    }

    public static void insertData(Connection connection) throws SQLException {
        String insertSQL = "INSERT INTO ad_soyad (first_name, last_name, position) VALUES (?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertSQL)) {
            insertStatement.setString(1, "Ali");
            insertStatement.setString(2, "Demir");
            insertStatement.setString(3, "Manager");
            insertStatement.executeUpdate();

            insertStatement.setString(1, "Ayse");
            insertStatement.setString(2, "Yilmaz");
            insertStatement.setString(3, "Developer");
            insertStatement.executeUpdate();

            insertStatement.setString(1, "Fatma");
            insertStatement.setString(2, "Kaya");
            insertStatement.setString(3, "Analyst");
            insertStatement.executeUpdate();

            System.out.println("Veri eklendi.");
        }
    }

    public static void readData(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM ad_soyad";
        try (PreparedStatement selectStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = selectStatement.executeQuery()) {

            System.out.println("Veriler:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") + ", First Name: " + resultSet.getString("first_name") +
                        ", Last Name: " + resultSet.getString("last_name") + ", Position: " + resultSet.getString("position"));
            }
            System.out.println();
        }
    }

    public static void deleteData(Connection connection) throws SQLException {
        String deleteSQLquery = "DELETE FROM ad_soyad WHERE last_name = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSQLquery)) {
            deleteStatement.setString(1, "Kaya");
            int rowsAffected = deleteStatement.executeUpdate();
            System.out.println(rowsAffected + " satır silindi.");
        }
    }

}
