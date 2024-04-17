package controllers;

import models.Bien;
import models.Usuario;
import utils.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;

public class ListarBienesUsuario {
    public static ArrayList<Bien> obtenerBienUsuario() throws ClassNotFoundException {
        ArrayList<Bien> bienes = new ArrayList<>();
        Connection conn = null;
        try { 
            conn = ConexionBD.getConnection();
            String sql = " SELECT b.nombre, b.placa, b.PK_Codigo" +
            "FROM MA_Bienes b" +
            "INNER JOIN MA_Usuarios u ON b.FK_Usuario = u.PK_idUsuario" + 
            "WHERE u.PK_idUsuario = '1'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bien bien = new Bien();
                bien.setNombre(rs.getString("nombre")); 
                bien.setPlaca(rs.getInt("placa"));
                bien.setCodigo(rs.getLong("PK_Codigo")); 
                Usuario usuario = new Usuario();
                usuario.setPK_idUsuario(rs.getInt("PK_idUsuario"));
                bien.setUsuario(usuario);    
                bienes.add(bien);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
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
        return bienes;
    }    
}
