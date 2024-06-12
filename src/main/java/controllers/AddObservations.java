package controllers;

import utils.ConnectionBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddObservations")
public class AddObservations extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer idUsuario = (Integer) session.getAttribute("idUsuario"); // Utiliza la misma clave que en el servlet Login
        String asunto = request.getParameter("asunto");
        String informacion = request.getParameter("informacion"); // Asegúrate de que coincida con el atributo 'name' del campo de texto en tu formulario HTML    

        try {
            Connection conn = ConnectionBD.getConnection();
            String sql = "INSERT INTO PA_BienPorUsuario (FK_Usuario, FK_Bien, asunto, informacion, fechaObservacion) VALUES (?, NULL, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setObject(1, idUsuario); // Utilizar setObject para permitir valores nulos
            statement.setString(2, asunto); // Utilizar la información como asunto
            statement.setString(3, informacion); // Ajustar según necesidades, por ejemplo, podría ser un campo adicional para descripción
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // Fecha actual
            statement.executeUpdate();
            System.out.println("Se ha insertado con éxito la observación");
            response.sendRedirect("homef.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al agregar la observación: " + e.getMessage());
            request.getRequestDispatcher("addobservation.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
