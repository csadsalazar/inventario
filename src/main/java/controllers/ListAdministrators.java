package controllers;

import models.Profile;
import models.User;
import utils.ConnectionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListAdministrators {
    public static ArrayList<User> getAdministrators() {
        ArrayList<User> users = new ArrayList<>();
        Connection conn = null; 
        try {
            conn = ConnectionBD.getConnection();
            String sql = "{CALL ListarAdministradores}";
            CallableStatement cs = (CallableStatement) conn.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setPK_idUser(rs.getInt("PK_idUsuario"));
                user.setUser(rs.getString("usuario"));
                Profile profile = new Profile();
                profile.setProfileName(rs.getString("nombrePerfil"));
                user.setPK_idProfile(profile);
                users.add(user); 
            }
            rs.close();
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
        return users;
    }
}