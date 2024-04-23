package controllers;

import models.Administrador;
import utils.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListarAdministradores {
    public static ArrayList<Administrador> obtenerAdministradores() {
        ArrayList<Administrador> administradores = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConexionBD.getConnection();
            String sql = "SELECT * FROM MA_Administradores";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Administrador admin = new Administrador();
                admin.setPK_idAdministrador(rs.getInt("PK_idAdministrador"));
                admin.setUsuario(rs.getString("usuario"));
                admin.setEstado(rs.getString("estado"));
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
