package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UpdateObjects")
public class UpdateObjects extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idsParam = request.getParameter("ids");
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        if (idsParam != null && !idsParam.isEmpty()) {
            String[] ids = idsParam.split(",");
            try {
                ListObjectsEdit.updateObjects(ids, name, description);
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
