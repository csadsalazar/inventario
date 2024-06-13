package controllers;

import models.Admin;
import utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListAdministrators {
    public static ArrayList<Admin> getAdministrators() {
        ArrayList<Admin> administradores = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionBD.getConnection();
            String sql = "SELECT * FROM MA_Administrador";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setPK_idAdministrador(rs.getInt("PK_idAdministrador"));
                admin.setUser(rs.getString("usuario"));
                admin.setState(rs.getString("estado"));
                administradores.add(admin);
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
        return administradores;
    }
}