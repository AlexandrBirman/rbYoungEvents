/*package dbService.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/rbYoung";
    private static final String USER = "postgres";
    private static final String PASS = "password";

    private ConnectionProvider() {}

    static {
        try {
            Class.forName(DB_DRIVER);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException("Cant find PSQL driver");
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Cant create connection to DataBase");
        }

        return connection;
    }
}
*/