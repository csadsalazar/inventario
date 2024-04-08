package controllers;

import models.Bien;
//import models.Usuario;
import utils.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;

public class HomeFuncionario {
    public static ArrayList<Bien> obtenerBienes() throws ClassNotFoundException {
        ArrayList<Bien> bienes = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConexionBD.getConnection();
            String query = "{CALL ListarBienesPorUsuario}";
            CallableStatement cs = (CallableStatement) conn.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Bien bien = new Bien();
                bien.setNombre(rs.getString("nombreBien"));
                bien.setCodigo(rs.getInt("PK_Codigo"));
                bien.setPlaca(rs.getString("placa"));
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
