package controllers;

import models.Bien;
import utils.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListarBienPorCodigo {
    public static Bien obtenerBienPorCodigo(int codigo) {
        Connection conn = null;
        Bien bien = null;
        try {
            conn = ConexionBD.getConnection();
            String sql = "SELECT * FROM MA_Bienes WHERE PK_Codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bien = new Bien();
                bien.setCodigo(rs.getInt("PK_Codigo"));
                bien.setPlaca(rs.getInt("placa"));
                bien.setNombre(rs.getString("nombre"));
                bien.setDescripcion(rs.getString("descripcion"));
                bien.setUbicacion(rs.getString("ubicacion"));
                bien.setValor(rs.getInt("valor"));
                bien.setEstado(rs.getString("estado"));
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
        return bien;
    }
}
