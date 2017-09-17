import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Julian on 18/09/2017.
 */
public class DataManipulationJDBC {

    public static void main(String[] args) {

        //Create new SQLiteDB object instance
        LocalSQLiteDB sqLiteDB = new LocalSQLiteDB("sqlite", "/Users/julia/Documents/Wellington ICT Graduate School/SWEN502/Code/Foundations_of_Databases", "EmployeeDB(1).sqlite");

        try(Connection c = sqLiteDB.connection()) {
            try(PreparedStatement stmt = c.prepareStatement("SELECT d.DeptName AS 'Department', ROUND(AVG(e.Salary),2) AS 'Average Salary of the Department' FROM Department as d, Employee as e, Work as w WHERE e.EmpID == w.EmpID AND d.DeptID == w.DeptID AND d.DeptID IN (SELECT d.DeptID FROM Employee as e, Work as w, Department as d WHERE e.EmpID == w.EmpID AND w.DeptID == d.DeptID AND w.Percent_Time == 100 AND e.Age < 30 GROUP BY d.DeptName HAVING COUNT(e.EmpID) > 5) GROUP BY d.DeptName;")){

                //Store the query results in a ResultSet object
                try(ResultSet r = stmt.executeQuery()) {
                    while (r.next()) {
                        System.out.print(r.getString("Department") + ": ");
                        System.out.print("$" + r.getString("Average Salary of the Department"));
                        System.out.println();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



}
