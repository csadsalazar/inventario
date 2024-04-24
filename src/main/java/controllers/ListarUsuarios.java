package controllers;

import models.Usuario;
import utils.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;

public class ListarUsuarios {
    public static ArrayList<Usuario> obtenerUsuarios() throws ClassNotFoundException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConexionBD.getConnection();
            String sql = "SELECT * FROM MA_Usuarios";   
            PreparedStatement cs = conn.prepareStatement(sql);   
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setPK_idUsuario(rs.getInt("PK_idUsuario"));
                usuario.setUsuario(rs.getString("usuario"));
                usuarios.add(usuario);
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
        return usuarios;
    }

    public static Usuario obtenerUsuarioPorId(int id) throws ClassNotFoundException {
        Usuario usuario = null;
        Connection conn = null;
        try {
            conn = ConexionBD.getConnection();
            String sql = "SELECT * FROM MA_Usuarios WHERE PK_idUsuario = ?";   
            PreparedStatement cs = conn.prepareStatement(sql);   
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setPK_idUsuario(rs.getInt("PK_idUsuario"));
                usuario.setUsuario(rs.getString("usuario"));
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
        return usuario;
    }
    
}
    
