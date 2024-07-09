package controllers;

import models.DocumentType;
import utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;

public class ListDocumentType {
    public static ArrayList<DocumentType> getDocumentType() throws ClassNotFoundException {
        ArrayList<DocumentType> documents = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionBD.getConnection();
            String sql = "SELECT * FROM PA_TipoDocumento";   
            PreparedStatement cs = conn.prepareStatement(sql);   
            ResultSet rs = cs.executeQuery(); 
            while (rs.next()) {
                DocumentType document = new DocumentType();
                document.setPK_idChargeType(rs.getInt("PK_idTipoDocumento"));
                document.setName(rs.getString("nombre"));
                documents.add(document);
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
        return documents;
    }

    public static DocumentType getDocumentTypeById(int id) throws ClassNotFoundException {
        DocumentType document = null;
        Connection conn = null;
        try {
            conn = ConnectionBD.getConnection();
            String sql = "SELECT * FROM PA_TipoDocumento WHERE PK_idTipoDocumento = ?";   
            PreparedStatement cs = conn.prepareStatement(sql);   
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                document = new DocumentType();
                document.setPK_idChargeType(rs.getInt("PK_idTipoDocumento"));
                document.setName(rs.getString("nombre"));
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
        return document;
    }
}