package repository.database;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import static java.lang.System.exit;

import domain.Patient;


public class databasePatientRepo extends databaseRepo<String, Patient> {
    public databasePatientRepo(String url) {
        super(url);
    }
    
    @Override
    protected void createTable() {
        try {
            openConnection();
            final Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS " +
                            "patients(" +
                            "PatientId VARCHAR(25) PRIMARY KEY, " +
                            "Name VARCHAR(200) NOT NULL , " +
                            "Email VARCHAR(200) NOT NULL , " +
                            "Phone VARCHAR(200) NOT NULL , " +
                            "Address VARCHAR(200) NOT NULL );");
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema pat : " + e.getMessage());
            exit(1);
        } finally {
            closeConnection();
        }
    }
    
    @Override
    public boolean add(String id, Patient t) {
        try {
            openConnection();
            final Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO patients(PatientId, Name, Email, Phone) " +
                            "VALUES('" + id + "', '" + t.getName() + "', '" + t.getEmail() + "', '" + t.getPhone() + "');" + t.getAddress());
        } catch (SQLException e) {
            System.err.println("[ERROR] add : " + e.getMessage());
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }

    @Override
    public boolean remove(String id) {
        try {
            openConnection();
            final Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "DELETE FROM patients WHERE PatientId = '" + id + "';");
        } catch (SQLException e) {
            System.err.println("[ERROR] remove : " + e.getMessage());
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }

    @Override
    public boolean update(String id, Patient t) {
        try {
            openConnection();
            final Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "UPDATE patients SET Name = '" + t.getName() + "', Email = '" + t.getEmail() + "', Phone = '" + t.getPhone() + ", Address = '" + t.getAddress() + "' WHERE PatientId = '" + id + "';");
        } catch (SQLException e) {
            System.err.println("[ERROR] update : " + e.getMessage());
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }

    @Override
    public Patient get(String id) {
        try {
            openConnection();
            final Statement statement = connection.createStatement();
            statement.executeQuery(
                    "SELECT * FROM patients WHERE PatientId = '" + id + "';");
        } catch (SQLException e) {
            System.err.println("[ERROR] get : " + e.getMessage());
            return null;
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public ArrayList<Patient> get() {
        try{
            openConnection();
            final Statement statement = connection.createStatement();
            statement.executeQuery(
                    "SELECT * FROM patients;");
        } catch (SQLException e) {
            System.err.println("[ERROR] get : " + e.getMessage());
            return null;
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public Iterator<Patient> iterator() {
        try {
            openConnection();
            final Statement statement = connection.createStatement();
            statement.executeQuery(
                    "SELECT * FROM patients;");
        } catch (SQLException e) {
            System.err.println("[ERROR] iterator : " + e.getMessage());
            return null;
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public int size() {
        try {
            openConnection();
            final Statement statement = connection.createStatement();
            statement.executeQuery(
                    "SELECT COUNT(*) FROM patients;");
        } catch (SQLException e) {
            System.err.println("[ERROR] size : " + e.getMessage());
            return 0;
        } finally {
            closeConnection();
        }
        return 0;
    }
}
