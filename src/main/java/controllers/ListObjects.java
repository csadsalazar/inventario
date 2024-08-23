package controllers;
import models.Dependency;
import models.Object;
import models.User;
import utils.ConnectionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;  
import java.util.ArrayList;
  
public class ListObjects {
    public static ArrayList<Object> getObjects() throws ClassNotFoundException {
        ArrayList<Object> bienes = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionBD.getConnection();
            String query = "EXEC AL_INV.ListarBienesActivos";
            CallableStatement cs = (CallableStatement) conn.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Object bien = new Object();
                bien.setCode(rs.getLong("PK_Codigo"));
                bien.setPlate(rs.getInt("placa"));
                bien.setName(rs.getString("nombre"));
                bien.setState(rs.getString("estado"));
                bien.setCondition(rs.getString("condicion"));
                bien.setDate(rs.getTimestamp("fecha"));
                bien.setDateadmin(rs.getTimestamp("fechaAdmin"));
                bien.setObservation(rs.getString("observacionAdmin"));
                User user = new User(); 
                user.setName(rs.getString("usuario"));
                bien.setUser(user); 
                User admin = new User();
                admin.setName(rs.getString("usuario_admin"));
                bien.setAdmin(admin);
                Dependency dependencia = new Dependency();
                dependencia.setDependencyname(rs.getString("nombredependencia"));
                bien.setPK_idDependency(dependencia);
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

    public static boolean allItemsReportedAndActive(int userId) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionBD.getConnection();
        try {
            // Contar bienes que están en estado 'Activo' y condición 'Reportado'
            String sql = "SELECT COUNT(*) FROM ADMINISTRATIVA.AL_INV.MA_Bien " +
                         "WHERE FK_Usuario = ? AND estado = 'Reportado' AND condicion = 'Activo'";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int countReportedAndActive = resultSet.getInt(1);
    
            // Contar todos los bienes del usuario que tienen estado 'Activo'
            sql = "SELECT COUNT(*) FROM ADMINISTRATIVA.AL_INV.MA_Bien " +
                  "WHERE FK_Usuario = ? AND condicion = 'Activo'";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            resultSet.next();
            int countAllActive = resultSet.getInt(1);
    
            return countReportedAndActive == countAllActive;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
} 