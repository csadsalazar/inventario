package controllers;

import models.Dependency;
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
                observacion.setAffair(rs.getString("asunto"));
                observacion.setInformation(rs.getString("informacion"));
                User usuario = new User();
                usuario.setUser(rs.getString("usuario"));
                observacion.setUser(usuario); 
                Dependency dependencia = new Dependency();
                dependencia.setDependencyName(rs.getString("nombreDependencia")); 
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