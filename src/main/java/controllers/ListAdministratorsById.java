package controllers;

import models.Admin;
import utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListAdministratorsById {
    public static Admin getAdministratorsById(int codigo) {
        Connection conn = null;
        Admin administrador = null;
        try {
            conn = ConnectionBD.getConnection();
            String sql = "SELECT * FROM MA_Administrador WHERE PK_idAdministrador=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                administrador = new Admin();
                administrador.setPK_idAdministrador(rs.getInt("PK_idAdministrador"));
                administrador.setUsuario(rs.getString("usuario"));
                administrador.setEstado(rs.getString("estado"));
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