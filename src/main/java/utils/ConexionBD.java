package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3309/Gestion_Inventario?useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            System.out.println("Intentando conectar a la base de datos...");
            Connection connection = getConnection();
            System.out.println("¡Conexión exitosa a la base de datos!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
