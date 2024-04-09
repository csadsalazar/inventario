package controllers;

import models.Observacion;
import models.Usuario;
import utils.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;

public class ListarObservaciones {
    public static ArrayList<Observacion> obtenerObservaciones() throws ClassNotFoundException {
        ArrayList<Observacion> observaciones = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConexionBD.getConnection();
            String query = "{CALL ListarObservaciones}";
            CallableStatement cs = (CallableStatement) conn.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Observacion observacion = new Observacion();
                observacion.setAsunto(rs.getString("asunto"));
                observacion.setInformacion(rs.getString("informacion"));
                Usuario usuario = new Usuario();
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setDependencia(rs.getString("dependencia"));
                observacion.setUsuario(usuario);
                observaciones.add(observacion);
            }            
            rs.close();
            cs.close();
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
        return observaciones;
    }    
}
