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
            "FROM MA_Bien b " +
            "INNER JOIN MA_Usuario u ON b.FK_Usuario = u.PK_idUsuario " +
            "INNER JOIN MA_Dependencia d ON b.FK_Dependencia = d.PK_idDependencia " +
            "WHERE b.PK_Codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bien = new Object();
                bien.setCode(rs.getLong("PK_Codigo")); 
                bien.setPlate(rs.getInt("placa"));
                bien.setName(rs.getString("nombre"));
                bien.setDescription(rs.getString("descripcion"));
                bien.setObservation(rs.getString("observacionAdmin"));
                bien.setDate(rs.getTimestamp("fecha"));
                Dependency dependencia = new Dependency();
                dependencia.setDependencyname(rs.getString("nombreDependencia"));
                bien.setPK_idDependency(dependencia);
                User usuario = new User();
                usuario.setPK_idUser(rs.getInt("idUsuario"));
                usuario.setUser(rs.getString("usuario"));
                bien.setUser(usuario);    
                bien.setValue(rs.getLong("valor"));
                bien.setState(rs.getString("estado"));
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