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

@WebServlet("/AddObservation")
public class AddObservation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idUsuario = (Integer) request.getSession().getAttribute("idUsuario"); // Corregido para manejar el valor null
        String asunto = request.getParameter("asunto");
        String informacion = request.getParameter("informacion");

        try {
            Connection conn = ConnectionBD.getConnection();
            String sql = "INSERT INTO PA_BienesPorUsuario (FK_Usuario, FK_Bien, asunto, informacion, fechaObservacion) VALUES (?, 1, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idUsuario); // Corregido para manejar el valor null
            statement.setString(2, asunto);
            statement.setString(3, informacion);
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