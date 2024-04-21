package controllers;

import models.Dependencia;
import utils.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;

public class ListarDependencias {
    public static ArrayList<Dependencia> obtenerDependencias() throws ClassNotFoundException {
        ArrayList<Dependencia> dependencias = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConexionBD.getConnection();
            String sql = "SELECT * FROM MA_Dependencias";   
            PreparedStatement cs = conn.prepareStatement(sql);   
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Dependencia dependencia = new Dependencia();
                dependencia.setPK_idDependencia(rs.getInt("PK_idDependencia"));
                dependencia.setCodigo(rs.getInt("codigo"));
                dependencia.setnombreDependencia(rs.getString("nombreDependencia"));
                // Agregar el objeto Dependencia a la lista
                dependencias.add(dependencia);
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
        return dependencias;
    }

    public static Dependencia obtenerDependenciaPorId(int id) throws ClassNotFoundException {
        Dependencia dependencia = null;
        Connection conn = null;
        try {
            conn = ConexionBD.getConnection();
            String sql = "SELECT * FROM MA_Dependencias WHERE PK_idDependencia = ?";   
            PreparedStatement cs = conn.prepareStatement(sql);   
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                dependencia = new Dependencia();
                dependencia.setPK_idDependencia(rs.getInt("PK_idDependencia"));
                dependencia.setCodigo(rs.getInt("codigo"));
                dependencia.setnombreDependencia(rs.getString("nombreDependencia"));
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
        return dependencia;
    }
    
}
    
