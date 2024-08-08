package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException; 
import java.sql.SQLException;  

@WebServlet("/UpdateMasiveObjects")
public class UpdateMasiveObjects extends HttpServlet {

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idsParam = request.getParameter("ids");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String observation = request.getParameter("observation"); 
        String state = request.getParameter("state");
        String dependency = request.getParameter("dependency");
        String user = request.getParameter("user");
        String condition = request.getParameter("condition");
        
        // Obtener el username desde la sesión para el administrador
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        if (username == null) {
            request.setAttribute("error", "La sesión ha expirado. Por favor, inicia sesión nuevamente.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        
        // Obtener el ID del administrador desde el username
        int adminId = 0;
        try {
            adminId = UserController.getUserIdByUsername(username);
            System.out.println(adminId);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al obtener el ID del administrador: " + e.getMessage());
            request.getRequestDispatcher("editselected.jsp").forward(request, response);
            return;
        }

        if (idsParam != null && !idsParam.isEmpty()) { 
            String[] ids = idsParam.split(",");
            try {
                ObjectsEditMasive.updateObjects(request, ids, name, description, observation, state, dependency, user, condition, String.valueOf(adminId));
                response.sendRedirect("managementobjects.jsp"); // Redirigir a una página de éxito
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al actualizar los datos.");
                request.getRequestDispatcher("editselected.jsp").forward(request, response); // Redirigir a una página de error
            }
        } else {
            request.setAttribute("error", "No se han seleccionado objetos.");
            request.getRequestDispatcher("editselected.jsp").forward(request, response); // Redirigir a una página de error
        }
    }
}