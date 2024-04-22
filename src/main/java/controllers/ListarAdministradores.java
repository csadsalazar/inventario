package controllers;

import models.Administrador;
import utils.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;

public class ListarAdministradores {
    public static ArrayList<Administrador> obtenerAdministradores() throws ClassNotFoundException {
        ArrayList<Administrador> administradores = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConexionBD.getConnection();
            String query = "SELECT * FROM MA_Administradores";
            CallableStatement cs = (CallableStatement) conn.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Administrador administrador = new Administrador();
                administrador.setUsuario(rs.getString("usuario"));
                administrador.setEstado(rs.getString("estado"));
                administradores.add(administrador);
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
        return administradores;
    }    
}

