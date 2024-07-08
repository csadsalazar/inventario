package controllers;

import models.Profile;
import models.User;
import utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListAdministratorsById {
    public static User getAdministratorsById(int codigo) { 
        Connection conn = null;
        User administrador = null;
        try {
            conn = ConnectionBD.getConnection();
            String sql = "SELECT * FROM MA_Usuario WHERE PK_idUsuario=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                administrador = new User();
                administrador.setPK_idUser(rs.getInt("PK_idUsuario"));
                administrador.setUser(rs.getString("usuario"));
                Profile profile = new Profile();
                profile.setPK_idProfile(rs.getInt("FK_Perfil"));
                administrador.setPK_idProfile(profile);
            }
            rs.close();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return administrador;
    }
}