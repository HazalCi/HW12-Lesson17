package org.example.databases.JDBI;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import java.util.List;
import java.util.Optional;

public class DatabaseOperations {
    private Jdbi jdbi;

    public DatabaseOperations(String url, String user, String password) {
        this.jdbi = Jdbi.create(url, user, password);
    }

    public void createTable() {
        try (Handle handle = jdbi.open()) {
            handle.execute("CREATE TABLE IF NOT EXISTS ad_soyad (" +
                    "id SERIAL PRIMARY KEY," +
                    "firstName VARCHAR(50)," +
                    "lastName VARCHAR(50)," +
                    "position VARCHAR(50)" +
                    ")");
            System.out.println("Tablo oluşturuldu.");
        } catch (UnableToExecuteStatementException e) {
            System.err.println("Tablo oluşturulamadı: " + e.getMessage());
        }
    }

    public void insertPersonnel(Personnel personnel) {
        try (Handle handle = jdbi.open()) {
            handle.execute("INSERT INTO ad_soyad (firstName, lastName, position) VALUES (?, ?, ?)",
                    personnel.getFirstName(), personnel.getLastName(), personnel.getPosition());
            System.out.println("Personel eklendi: " + personnel);
        } catch (UnableToExecuteStatementException e) {
            System.err.println("Personel eklenemedi: " + e.getMessage());
        }
    }

    public Personnel getPersonnelById(int id) {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery("SELECT * FROM ad_soyad WHERE id = :id")
                    .bind("id", id)
                    .mapToBean(Personnel.class)
                    .one();
        } catch (UnableToExecuteStatementException e) {
            System.err.println("Personel bilgisi alınamadı: " + e.getMessage());
            return null;
        }
    }


    public void updatePersonnel(int id, String newPosition) {
        try (Handle handle = jdbi.open()) {
            Optional<Long> update = handle.createUpdate("UPDATE ad_soyad SET position = :position WHERE id = :id")
                    .bind("position", newPosition)
                    .bind("id", id)
                    .executeAndReturnGeneratedKeys()
                    .mapTo(Long.class)
                    .findFirst();

            System.out.println("Personel pozisyonu güncellendi: id=" + id + " Yeni pozisyon:" + newPosition);

        } catch (UnableToExecuteStatementException e) {
            System.err.println("Personel pozisyonu güncellenemedi: " + e.getMessage());
        }
    }

    public void deletePersonnel(int id) {
        try (Handle handle = jdbi.open()) {
            handle.execute("DELETE FROM ad_soyad WHERE id = ?", id);
            System.out.println("Personel silindi: id=" + id);
        } catch (UnableToExecuteStatementException e) {
            System.err.println("Personel silinemedi: " + e.getMessage());
        }
    }

    public List<Personnel> getAllPersonnel() {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery("SELECT * FROM ad_soyad")
                    .mapToBean(Personnel.class)
                    .list();
        } catch (UnableToExecuteStatementException e) {
            System.err.println("Tüm personel listesi alınamadı: " + e.getMessage());
            return null;
        }
    }
}

