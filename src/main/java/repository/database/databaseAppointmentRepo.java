package repository.database;

import domain.Appointment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import static java.lang.System.exit;


public class databaseAppointmentRepo extends databaseRepo<String, Appointment>{
    public databaseAppointmentRepo(String url) {
        super(url);
    }

    @Override
    protected void createTable() {
        try {
            openConnection();
            final Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS " +
                            "appointments(" +
                            "AppointmentId VARCHAR(25) PRIMARY KEY, " +
                            "PatientId VARCHAR(25) NOT NULL , " +
                            "DentistId VARCHAR(25) NOT NULL , " +
                            "Date VARCHAR(200) NOT NULL , " +
                            "Description VARCHAR(200) NOT NULL , " +
                            "FOREIGN KEY(PatientId) REFERENCES patients(PatientId), " +
                            "FOREIGN KEY(DentistId) REFERENCES dentists(DentistId));");
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema app : " + e.getMessage());
            exit(1);
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean add(String id, Appointment t) {
        try {
            openConnection();
            final Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO appointments(AppointmentId, PatientId, DentistId, Date, Description) " +
                            "VALUES('" + id + "', '" + t.getPatientId() + "', '" + t.getDentistId() + "', '" + t.getDate() + "', '" + t.getDescription() + "');");
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
                    "DELETE FROM appointments " +
                            "WHERE AppointmentId = '" + id + "';");
        } catch (SQLException e) {
            System.err.println("[ERROR] remove : " + e.getMessage());
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }

    @Override
    public boolean update(String id, Appointment t) {
        try {
            openConnection();
            final Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "UPDATE appointments " +
                            "SET PatientId = '" + t.getPatientId() + "', " +
                            "DentistId = '" + t.getDentistId() + "', " + "Date = '" + t.getDate() + "', " + "Description = '" + t.getDescription() + "' " +
                            "WHERE AppointmentId = '" + id + "';");
        } catch (SQLException e) {
            System.err.println("[ERROR] update : " + e.getMessage());
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }

    @Override
    public Appointment get(String id) {
        try {
            openConnection();
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(
                    "SELECT * " +
                            "FROM appointments " +
                            "WHERE AppointmentId = '" + id + "';");
            if (resultSet.next()) {
                return Appointment.build(id, "", "", "", "");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] get : " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public ArrayList<Appointment> get() {
        try{
            openConnection();
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(
                    "SELECT * " +
                            "FROM appointments;");
            ArrayList<Appointment> appointments = new ArrayList<>();
            while (resultSet.next()) {
                appointments.add(Appointment.build(resultSet.getString("AppointmentId"), resultSet.getString("PatientId"), resultSet.getString("DentistId"), resultSet.getString("Date"), resultSet.getString("Description")));
            }
            return appointments;
        } catch (SQLException e) {
            System.err.println("[ERROR] get : " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public Iterator<Appointment> iterator() {
        try{
            openConnection();
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(
                    "SELECT * " +
                            "FROM appointments;");
            ArrayList<Appointment> appointments = new ArrayList<>();
            while (resultSet.next()) {
                appointments.add(Appointment.build(resultSet.getString("AppointmentId"), resultSet.getString("PatientId"), resultSet.getString("DentistId"), resultSet.getString("Date"), resultSet.getString("Description")));
            }
            return appointments.iterator();
        } catch (SQLException e) {
            System.err.println("[ERROR] get : " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public int size() {
        try{
            openConnection();
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(
                    "SELECT COUNT(*) " +
                            "FROM appointments;");
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] get : " + e.getMessage());
        } finally {
            closeConnection();
        }
        return 0;
    }
}