package controllers;

import models.Dependency;
import models.Object;
import models.User;
import utils.ConnectionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
  
public class ListObjects {
    public static ArrayList<Object> getObjects() throws ClassNotFoundException {
        ArrayList<Object> bienes = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionBD.getConnection();
            String query = "{CALL ListarBienesActivos}";
            CallableStatement cs = (CallableStatement) conn.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Object bien = new Object();
                bien.setCode(rs.getLong("PK_Codigo"));
                bien.setPlate(rs.getInt("placa"));
                bien.setName(rs.getString("nombre"));
                bien.setState(rs.getString("estado"));
                bien.setCondition(rs.getString("condicion"));
                bien.setDate(rs.getDate("fecha"));
                bien.setObservation(rs.getString("observacionAdmin"));
                User user = new User(); 
                user.setName(rs.getString("usuario"));
                bien.setUser(user); 
                User admin = new User();
                admin.setName(rs.getString("usuario_admin"));
                bien.setAdmin(admin);
                Dependency dependencia = new Dependency();
                dependencia.setDependencyname(rs.getString("nombredependencia"));
                bien.setPK_idDependency(dependencia);
                bienes.add(bien);
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
        return bienes;
    }    
} 