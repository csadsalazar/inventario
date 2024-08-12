package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:sqlserver://SRVVSANDIEGO\\SRVDESARROLLO:1433;databaseName=ADMINISTRATIVA;trustServerCertificate=true";
        String username = "csalazart";
        String password = "Colombia2023*";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            System.out.println("Attempting to connect to the database...");
            Connection connection = getConnection();
            System.out.println("Connected to the database!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
