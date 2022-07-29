package database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBInfo {
    public static final String url = "jdbc:mysql://localhost:3306/finaldb";
    public static final String username = "root";
    public static final String Pass = "";
    public static java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, Pass);
    }
}
