package controllers;

import models.Administrador;
import utils.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListarAdminPorCodigo {
    public static Administrador obtenerAdminPorCodigo(int codigo) {
        Connection conn = null;
        Administrador administrador = null; 
        try {
            conn = ConexionBD.getConnection();
            String sql = "SELECT usuario, estado FROM MA_Administradores WHERE PK_idAdministrador = ?";
            PreparedStatement ps = conn.prepareStatement(sql); 
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                administrador = new Administrador();
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
