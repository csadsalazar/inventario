package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.ConnectionBD;

@WebServlet("/EditMasive")
public class EditMasive extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedIdsStr = request.getParameter("selectedIds");
        
        if (selectedIdsStr == null || selectedIdsStr.isEmpty()) {
            // Manejar caso donde no se seleccionaron registros
            response.sendRedirect("managementobjects.jsp");
            return;
        }
        
        String[] selectedIds = selectedIdsStr.split(",");
        
        // Aquí puedes realizar operaciones con cada ID seleccionado
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionBD.getConnection();
            String sql = "UPDATE MA_Bien SET estado=? WHERE PK_Codigo=?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "Pruebaaaa");
            
            for (String idStr : selectedIds) {
                long id = Long.parseLong(idStr.trim());
                statement.setLong(2, id);
                statement.executeUpdate();
                System.out.println("Se ha actualizado el bien con id: " + id);
            }
            // Después de actualizar los IDs seleccionados en EditMasive.java
            request.getRequestDispatcher("editmassive.jsp").forward(request, response);

            
            // Redirigir después de la actualización exitosa
            response.sendRedirect("managementobjects.jsp");
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Manejo de errores
            request.setAttribute("error", "Error al actualizar los bienes: " + e.getMessage());
            request.getRequestDispatcher("managementobjects.jsp").forward(request, response);
            
        } finally {
            // Cerrar recursos
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}