package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class H2Factory {
    private static Connection connection;

    public static Connection getConnection() {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String URL = "jdbc:h2:tcp://localhost/~/test";
        final String USER = "sa";
        final String PASSWORD = "";

        if (connection == null) {
            try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                return connection;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
