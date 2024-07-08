package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteObjectsServlet")
public class DeleteObjectsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selectedObjects = request.getParameterValues("selectedObjects");
        if (selectedObjects != null) {
            for (String codigo : selectedObjects) {
                try {
                    // Aquí debes eliminar cada objeto individualmente utilizando su código único
                    DeleteObject deleteObject = new DeleteObject();
                    deleteObject.deleteObject(codigo);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        response.sendRedirect("managementobjects.jsp");
    }
}
