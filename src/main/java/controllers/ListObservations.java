package controllers;

import models.Observation;
import models.User;
import utils.ConnectionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;

public class ListObservations {
    public static ArrayList<Observation>getObservations() throws ClassNotFoundException {
        ArrayList<Observation> observaciones = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionBD.getConnection();
            String query = "{CALL ListarObservaciones}";
            CallableStatement cs = (CallableStatement) conn.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Observation observacion = new Observation();
                observacion.setAsunto(rs.getString("asunto"));
                observacion.setInformacion(rs.getString("informacion"));
                User usuario = new User();
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