package controllers;

import models.Charge;
import utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;

public class ListCharge {
    public static ArrayList<Charge> getCharges() throws ClassNotFoundException {
        ArrayList<Charge> charges = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionBD.getConnection();
            String sql = "SELECT * FROM PA_Cargo";   
            PreparedStatement cs = conn.prepareStatement(sql);   
            ResultSet rs = cs.executeQuery();  
            while (rs.next()) {
                Charge charge = new Charge();
                charge.setPK_idCharge(rs.getInt("PK_idCargo"));
                charge.setName(rs.getString("nombre"));
                charges.add(charge);
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
        return charges;
    }

    public static Charge getChargeById(int id) throws ClassNotFoundException {
        Charge charge = null;
        Connection conn = null;
        try {
            conn = ConnectionBD.getConnection();
            String sql = "SELECT * FROM PA_Cargo WHERE PK_idCargo = ?";   
            PreparedStatement cs = conn.prepareStatement(sql);   
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                charge = new Charge();
                charge.setPK_idCharge(rs.getInt("PK_idCargo"));
                charge.setName(rs.getString("nombre"));
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
        return charge;
    }
}