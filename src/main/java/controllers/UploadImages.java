package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
  
@WebServlet("/DeleteAdmin")
public class UploadImages extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        boolean eliminado = false;
        try {
            eliminado = deleteAdmin(codigo);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
    } 
 
    private boolean deleteAdmin(String codigo) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null; 
        try {
            conn = ConnectionBD.getConnection(); // Utiliza tu método para obtener la conexión 
            String sql = "UPDATE MA_Bien SET Imagen1=?, Imagen2=?, Imagen3=? WHERE PK_Codigo= ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, codigo);
            sstmt.setString(1, codigo);
            stmt.setString(1, codigo);
            stmt.setString(1, codigo);
            int filasAfectadas = stmt.executeUpdate();
            System.out.println("Se ha eliminado el admin con código: " + codigo);
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally { 
            // Cierra la conexión y el statement
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } 
}