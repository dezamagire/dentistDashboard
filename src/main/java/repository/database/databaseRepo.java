package repository.database;

import java.sql.Connection;
import java.sql.SQLException;
import org.sqlite.SQLiteDataSource;
import static java.lang.System.exit;

import repository.Repository;


public abstract class databaseRepo<ID, T> implements Repository<ID, T> {
    protected String url;

    protected Connection connection;

    public databaseRepo(String url) {
        this.url = "jdbc:sqlite:" + url;
        createTable();
    }

    protected void openConnection() {
        try {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(this.url);
            if (connection == null || connection.isClosed())
                connection = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            exit(1);
        }
    }
    
    protected void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
            exit(1);
        }
    }

    abstract protected void createTable();

    @Override
    public String toString() {
        if (get().size() == 0)
            return "Repository is empty";
        String output = "";
        for (T t : get()) {
            output += t.toString() + "\n";
        }
        return output;
    }
}
