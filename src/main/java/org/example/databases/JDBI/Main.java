package org.example.databases.JDBI;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.JdbiException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7719931";
        String USER = "sql7719931";
        String PASSWORD = "YPJq1ilLz1";

        DatabaseOperations dbOperation = new DatabaseOperations(URL, USER, PASSWORD);

        dbOperation.createTable();

        Personnel personnel1 = new Personnel("Ali", "Demir", "Manager");
        Personnel personnel2 = new Personnel("Ayse", "Yilmaz", "Developer");
        Personnel personnel3 = new Personnel("Fatma", "Kaya", "Analyst");

        dbOperation.insertPersonnel(personnel1);
        dbOperation.insertPersonnel(personnel2);
        dbOperation.insertPersonnel(personnel3);

        Personnel personnel = dbOperation.getPersonnelById(1);
        if (personnel != null) {
            System.out.println("Alınan personel: " + personnel);
        }

        dbOperation.updatePersonnel(1, "Tester");

        dbOperation.deletePersonnel(2);

        List<Personnel> personnels = dbOperation.getAllPersonnel();
        if (personnels != null) {
            personnels.forEach(System.out::println);
        }
    }
}
