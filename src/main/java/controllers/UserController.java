package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.ConnectionBD;

public class UserController {
    public static boolean userExists(String usuario) {
        Connection conn = null;
        try {
            conn = ConnectionBD.getConnection();
            String sql = "SELECT * FROM MA_Usuario WHERE usuario=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Si hay resultados, el usuario existe
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getUserId(String usuario) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionBD.getConnection();
        String sql = "SELECT PK_idUsuario FROM MA_Usuario WHERE usuario=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, usuario);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("PK_idUsuario");
        } else {
            return -1; // Si no se encuentra el usuario, devolver un valor negativo
        }
    }


    // Método para obtener el ID del usuario usando el username
    public static int getUserIdByUsername(String username) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionBD.getConnection();
        String sql = "SELECT PK_idUsuario FROM MA_Usuario WHERE usuario = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
     
        if (resultSet.next()) {
            return resultSet.getInt("PK_idUsuario");
        } else {
            throw new SQLException("Usuario no encontrado");
        }
    }
}