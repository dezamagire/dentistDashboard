package repository.database;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.System.exit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Dentist;

public class databaseDentistRepo extends databaseRepo<String, Dentist>{    
    public databaseDentistRepo(String url) {
        super(url);
    }
    
    @Override
    protected void createTable() {
        try {
            openConnection();
            final Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS " +
                            "dentists(" +
                            "DentistId VARCHAR(25) PRIMARY KEY, " +
                            "Name VARCHAR(200) NOT NULL , " +
                            "Email VARCHAR(200) NOT NULL , " +
                            "Phone VARCHAR(200) NOT NULL );");
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema dent : " + e.getMessage());
            exit(1);
        } finally {
            closeConnection();
        }
    }
    
    @Override
    public boolean add(String id, Dentist t) {
        try {
            openConnection();
            final Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO dentists(DentistId, Name, Email, Phone) " +
                            "VALUES('" + id + "', '" + t.getName() + "', '" + t.getEmail() + "', '" + t.getPhone() + "');");
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
                    "DELETE FROM dentists WHERE DentistId = '" + id + "';");
        } catch (SQLException e) {
            System.err.println("[ERROR] remove : " + e.getMessage());
            return false;
        } finally {
            closeConnection();
        }
        return false;
    }

    @Override
    public boolean update(String id, Dentist t) {
        try {
            openConnection();
            final Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "UPDATE dentists SET Name = '" + t.getName() + "', Email = '" + t.getEmail() + "', Phone = '" + t.getPhone() + "' WHERE DentistId = '" + id + "';");
        } catch (SQLException e) {
            System.err.println("[ERROR] update : " + e.getMessage());
            return false;
        } finally {
            closeConnection();
        }
        return false;
    }

    @Override
    public ArrayList<Dentist> get() {
        var dentists = new ArrayList<Dentist>();
        try {
            openConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * from dentists;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                var d = new Dentist(
                        rs.getString("DentistId"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Phone"));
                dentists.add(d);
            }
            return dentists;
        } catch (SQLException ex) {
            return null;
        } finally {
            closeConnection();
        }
    }

    @Override
    public Dentist get(String id) {
        try {
            openConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * from dentists WHERE DentistId = '" + id + "';");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                var d = new Dentist(
                        rs.getString("DentistId"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Phone"));
                return d;
            }
        } catch (SQLException ex) {
            return null;
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public Iterator<Dentist> iterator() {
        try{
            openConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * from dentists;");
            ResultSet rs = statement.executeQuery();
            return new Iterator<Dentist>() {
                @Override
                public boolean hasNext() {
                    try {
                        return rs.next();
                    } catch (SQLException e) {
                        return false;
                    }
                }

                @Override
                public Dentist next() {
                    try {
                        var d = new Dentist(
                                rs.getString("DentistId"),
                                rs.getString("Name"),
                                rs.getString("Email"),
                                rs.getString("Phone"));
                        return d;
                    } catch (SQLException e) {
                        return null;
                    }
                }
            };
        } catch (SQLException e) {
            return null;
        } finally {
            closeConnection();
        }
    }

    @Override
    public int size() {
        try {
            openConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(*) from dentists;");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return 0;
        } finally {
            closeConnection();
        }
        return 0;
    }
}