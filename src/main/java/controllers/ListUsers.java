package controllers;

import models.User;
import utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;

public class ListUsers {
    public static ArrayList<User> getUsers() throws ClassNotFoundException {
        ArrayList<User> usuarios = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionBD.getConnection();
            String sql = "SELECT * FROM MA_Usuario";   
            PreparedStatement cs = conn.prepareStatement(sql);   
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                User usuario = new User();
                usuario.setPK_idUser(rs.getInt("PK_idUsuario"));
                usuario.setUser(rs.getString("usuario"));
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

    public static User getUsersById(int id) throws ClassNotFoundException {
        User usuario = null;
        Connection conn = null;
        try {
            conn = ConnectionBD.getConnection();
            String sql = "SELECT * FROM MA_Usuario WHERE PK_idUsuario = ?";   
            PreparedStatement cs = conn.prepareStatement(sql);   
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                usuario = new User();
                usuario.setPK_idUser(rs.getInt("PK_idUsuario"));
                usuario.setUser(rs.getString("usuario"));
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