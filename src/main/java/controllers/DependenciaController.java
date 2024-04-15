package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Dependencia;
import utils.ConexionBD;

public class DependenciaController {
    
    public static List<Dependencia> obtenerDependencias() {
        List<Dependencia> dependencias = new ArrayList<>();
        
        try {
            Connection conn = ConexionBD.getConnection();
            String sql = "SELECT * FROM MA_Dependencias";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("PK_idDependencia");
                String nombre = rs.getString("nombreDependencia");
                Dependencia dependencia = new Dependencia(id, nombre);
                dependencias.add(dependencia);
            }
            
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return dependencias;
    }
}
