/**
 * Created by Julian on 18/09/2017.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*Setup abstract DB class to store JDBC connection*/
public class AbstractDB {

    protected final String jdbcName;
    protected final String filepath;
    protected final String database;

    public AbstractDB(String jdbcName, String filepath, String database) {
        this.jdbcName = jdbcName;
        this.filepath = filepath;
        this.database = database;
    }

    public String connectionString() {
        return "jdbc:" + this.jdbcName + ":" + this.filepath + "/" + this.database;
    }

    public Connection connection() throws ClassNotFoundException, SQLException {
        Connection c = null;

        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection(connectionString());
        System.out.println("Connection to SQLite has been established");

        return c;
    }
}

