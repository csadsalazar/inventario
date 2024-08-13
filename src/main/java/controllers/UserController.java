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
            String sql = "SELECT * FROM ADMINISTRATIVA.AL_INV.MA_Usuario WHERE usuario=?";
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
        String sql = "SELECT PK_idUsuario FROM ADMINISTRATIVA.AL_INV.MA_Usuario WHERE usuario=?";
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
        String sql = "SELECT PK_idUsuario FROM ADMINISTRATIVA.AL_INV.MA_Usuario WHERE usuario = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
     
        if (resultSet.next()) {
            return resultSet.getInt("PK_idUsuario");
        } else {
            throw new SQLException("Usuario no encontrado");
        }
    }

        public static String getUserEmailById(int userId) throws SQLException, ClassNotFoundException {
            String email = null;
            Connection conn = ConnectionBD.getConnection();
            String sql = "SELECT correo FROM ADMINISTRATIVA.AL_INV.MA_Usuario WHERE PK_idUsuario=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                email = resultSet.getString("correo");
            }
            resultSet.close();
            statement.close();
            conn.close();
            return email;
        }

        public static String getUserNameById(int userId) throws SQLException, ClassNotFoundException {
            String nombre = null;
            Connection conn = ConnectionBD.getConnection();
            String sql = "SELECT nombre FROM ADMINISTRATIVA.AL_INV.MA_Usuario WHERE PK_idUsuario=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                nombre = resultSet.getString("nombre");
            }
            resultSet.close();
            statement.close();
            conn.close();
            return nombre;
        }

        // Método para obtener el ID del usuario usando el username
        public static int getUserIdByUsernameLogin(String username) throws SQLException, ClassNotFoundException {
            int userId = -1;
            String query = "SELECT PK_idUsuario FROM ADMINISTRATIVA.AL_INV.MA_Usuario WHERE usuario = ?";
    
            try (Connection conn = ConnectionBD.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
    
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();
    
                if (rs.next()) {
                    userId = rs.getInt("PK_idUsuario");
                }
            }
    
            return userId;
        }
    }