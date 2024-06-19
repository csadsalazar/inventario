package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateObjectsServlet")
public class UpdateObjectsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        int dependenciaId = Integer.parseInt(request.getParameter("dependencia"));
        String descripcion = request.getParameter("descripcion");
        String estado = request.getParameter("estado");
        String[] selectedObjects = request.getParameterValues("selectedObjects");

        if (selectedObjects != null && selectedObjects.length > 0) {
            EditObject editObject = new EditObject();
            try {
                if (editObject.editObject(usuario, dependenciaId, descripcion, estado, selectedObjects)) {
                    // Redirigir después de la actualización exitosa
                    response.sendRedirect("managementobjects.jsp");
                } else {
                    // Manejar el caso de error en la actualización
                    System.out.println("No se encuentra los datos...");
                    response.sendRedirect("managementobjects.jsp"); // Puedes redirigir a una página de error o manejar de otra forma
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("managementobjects.jsp"); // Manejar errores de clase no encontrada
            }
        } else {
            // Manejar el caso donde no se seleccionaron objetos para editar
            response.sendRedirect("managementobjects.jsp"); // Puedes redirigir a la página de gestión de objetos
        }
    }
}
