package controllers;
import models.Dependency;
import utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;

public class ListDependencies {
    public static ArrayList<Dependency> getDependencies() throws ClassNotFoundException {
        ArrayList<Dependency> dependencias = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionBD.getConnection();
            String sql = "SELECT * FROM ADMINISTRATIVA.AL_INV.Dependencia";   
            PreparedStatement cs = conn.prepareStatement(sql);   
            ResultSet rs = cs.executeQuery(); 
            while (rs.next()) {
                Dependency dependencia = new Dependency(); 
                dependencia.setPK_idDependency(rs.getInt("PK_idDependencia"));
                dependencia.setCostcenter(rs.getString("centroDeCosto"));
                dependencia.setDependencyname(rs.getString("nombreDependencia"));
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

    public static Dependency getDependencyById(int id) throws ClassNotFoundException {
        Dependency dependencia = null;
        Connection conn = null;
        try { 
            conn = ConnectionBD.getConnection();
            String sql = "SELECT * FROM ADMINISTRATIVA.AL_INV.Dependencia WHERE PK_idDependencia = ?";   
            PreparedStatement cs = conn.prepareStatement(sql);   
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                dependencia = new Dependency();
                dependencia.setPK_idDependency(rs.getInt("PK_idDependencia"));
                dependencia.setCostcenter(rs.getString("centroDeCosto"));
                dependencia.setDependencyname(rs.getString("nombreDependencia"));
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

    public static String getNameDependencyById(int dependencyId) throws SQLException, ClassNotFoundException {
        String nombre = null;
        Connection conn = ConnectionBD.getConnection();
        String sql = "SELECT nombreDependencia FROM ADMINISTRATIVA.AL_INV.Dependencia WHERE PK_idDependencia=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, dependencyId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            nombre = resultSet.getString("nombreDependencia");
        }
        resultSet.close();
        statement.close();
        conn.close();
        return nombre;
    }
}