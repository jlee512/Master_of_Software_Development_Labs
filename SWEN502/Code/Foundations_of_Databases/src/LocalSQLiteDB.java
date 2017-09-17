/**
 * Created by Julian on 18/09/2017.
 */
import java.sql.DriverManager;

public class LocalSQLiteDB extends AbstractDB {

    public LocalSQLiteDB(String jdbcName, String filepath, String filename) {
        super(jdbcName, filepath, filename);
    }

}
