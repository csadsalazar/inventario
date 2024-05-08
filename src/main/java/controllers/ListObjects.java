package controllers;

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
            String query = "{CALL ListarBienes}";
            CallableStatement cs = (CallableStatement) conn.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Object bien = new Object();
                bien.setCodigo(rs.getLong("PK_Codigo"));
                bien.setPlaca(rs.getInt("placa"));
                bien.setNombre(rs.getString("nombre"));
                User usuario = new User();
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setDependencia(rs.getString("dependencia"));
                bien.setUsuario(usuario);
                //bien.setValor(rs.getLong("valor"));
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