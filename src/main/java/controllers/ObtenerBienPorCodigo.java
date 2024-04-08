package controllers;

import models.Bien;
import models.Observacion;
import models.Usuario;
import utils.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObtenerBienPorCodigo {
    public static Bien obtenerBienPorCodigo(int codigo) {
        Connection conn = null;
        Bien bien = null;
        try {
            conn = ConexionBD.getConnection();
            String query = "SELECT b.PK_Codigo, b.placa, b.nombre, b.descripcion, b.ubicacion, b.observaciones, u.nombre AS nombreUsuario, u.dependencia, u.cargo, u.sede " +
                           "FROM MA_Bienes b " +
                           "INNER JOIN MA_Usuarios u ON b.FK_Usuario = u.PK_idUsuario " +
                           "WHERE b.PK_Codigo = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bien = new Bien();
                bien.setCodigo(rs.getInt("PK_Codigo"));
                bien.setPlaca(rs.getString("placa"));
                bien.setNombre(rs.getString("nombre"));
                bien.setDescripcion(rs.getString("descripcion"));
                bien.setUbicacion(rs.getString("ubicacion"));
                // Crear un objeto Observacion y asignarlo al objeto Bien
                Observacion observacion = new Observacion();
                observacion.setObservaciones(rs.getString("observaciones"));
                bien.setObservacion(observacion);
                // Crear un objeto Usuario y asignarlo al objeto Bien
                Usuario usuario = new Usuario();
                usuario.setNombre(rs.getString("nombreUsuario"));
                usuario.setDependencia(rs.getString("dependencia"));
                usuario.setCargo(rs.getString("cargo"));
                usuario.setSede(rs.getString("sede"));
                bien.setUsuario(usuario);
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
