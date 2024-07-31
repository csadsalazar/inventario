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
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;
import utils.ConnectionBD;

@WebServlet("/ActiveCondition")
public class ActiveCondition extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedIdsStr = request.getParameter("selectedIds");
        String[] selectedIds = selectedIdsStr.split(",");
        
        // Aquí puedes realizar operaciones con cada ID seleccionado
        for (String idStr : selectedIds) {            
            // Obtener el username desde la sesión
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            
            // Verificar si el username está presente en la sesión
            if (username == null) {
                // Manejar el caso donde el username no está en la sesión (por ejemplo, redirigir a la página de inicio de sesión)
                request.setAttribute("error", "La sesión ha expirado. Por favor, inicia sesión nuevamente.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }      
            // Obtener el ID del usuario usando el username
            int idUsuarioAdmin = 0;
            try {
                idUsuarioAdmin = UserController.getUserIdByUsername(username);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            long id = Long.parseLong(idStr);
            // Ejemplo de actualización (adaptar según tu esquema y necesidades)
            try {
                Connection conn = ConnectionBD.getConnection();  
                String sql = "UPDATE MA_Bien SET condicion=?, FK_UsuarioAdmin=?, fechaAdmin=? WHERE PK_Codigo=?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, "Activo");
                statement.setInt(2, idUsuarioAdmin);
                statement.setTimestamp(3, new Timestamp(System.currentTimeMillis())); // Fecha actual
                statement.setLong(4, id);
                statement.executeUpdate();
                System.out.println("se ha actualizado el o los bienes con id:" + id);
                conn.close(); // Cerrar conexión después de cada operación
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                // Manejo de errores
                request.setAttribute("error", "Error al actualizar el bien: " + e.getMessage());
                request.getRequestDispatcher("managementobjects.jsp").forward(request, response);
                return; // Salir del método si hay un error
            }
        }
        // Redirigir después de la actualización
        response.sendRedirect("managementobjects.jsp");
    }
}