package controllers;

import utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Dependency;
import models.User;
import models.Object;

public class ListObjectById {
    public static Object getObjectById(long codigo) {
        Connection conn = null;
        Object bien = null;
        try {
            conn = ConnectionBD.getConnection();
            String sql = "SELECT b.*, u.PK_idUsuario as idUsuario, u.usuario, d.nombreDependencia " +
            "FROM MA_Bienes b " +
            "INNER JOIN MA_Usuarios u ON b.FK_Usuario = u.PK_idUsuario " +
            "INNER JOIN MA_Dependencias d ON b.FK_Dependencia = d.PK_idDependencia " +
            "WHERE b.PK_Codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bien = new Object();
                bien.setCodigo(rs.getLong("PK_Codigo")); 
                bien.setPlaca(rs.getInt("placa"));
                bien.setNombre(rs.getString("nombre"));
                bien.setDescripcion(rs.getString("descripcion"));
                bien.setFecha(rs.getDate("fecha"));
                Dependency dependencia = new Dependency();
                dependencia.setnombreDependencia(rs.getString("nombreDependencia"));
                bien.setDependencia(dependencia);
                User usuario = new User();
                usuario.setPK_idUsuario(rs.getInt("idUsuario")); // Establecer el ID del usuario
                usuario.setUsuario(rs.getString("usuario"));
                bien.setUsuario(usuario);    
                bien.setValor(rs.getLong("valor"));
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