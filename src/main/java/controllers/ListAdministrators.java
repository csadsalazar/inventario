package controllers;

import models.Profile;
import models.User;
import utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListAdministrators {
    public static ArrayList<User> getAdministrators() {
        ArrayList<User> users = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionBD.getConnection();
            String sql = "SELECT FROM MA_Usuario";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUser(rs.getString("usuario"));
                Profile profile = new Profile();
                profile.setPK_idProfile(rs.getInt("PK_Perfil"));
                user.setProfile(profile);
                users.add(user); 
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
        return users;
    }
}