package controllers;

import models.Bien;
import models.Dependencia;
import models.Usuario;
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
            String sql = "SELECT b.*, u.usuario, d.nombreDependencia " +
            "FROM MA_Bienes b " +
            "INNER JOIN MA_Usuarios u ON b.FK_Usuario = u.PK_idUsuario " +
            "INNER JOIN MA_Dependencias d ON b.FK_Dependencia = d.PK_idDependencia " +
            "WHERE b.PK_Codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bien = new Bien();
                bien.setCodigo(rs.getInt("PK_Codigo"));
                bien.setPlaca(rs.getInt("placa"));
                bien.setNombre(rs.getString("nombre"));
                bien.setDescripcion(rs.getString("descripcion"));
                Dependencia dependencia = new Dependencia();
                dependencia.setnombreDependencia(rs.getString("nombreDependencia"));
                bien.setDependencia(dependencia);
                Usuario usuario = new Usuario();
                usuario.setUsuario(rs.getString("usuario"));
                bien.setUsuario(usuario);    
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
