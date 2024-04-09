package controllers;

import utils.ConexionBD;

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

@WebServlet("/AgregarObservacion")
public class AgregarObservacion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuarioParam = request.getParameter("usuario");
        int usuario = 1; // Valor predeterminado o valor adecuado en caso de que el parámetro sea nulo
        if (usuarioParam != null && !usuarioParam.isEmpty()) {
            usuario = Integer.parseInt(usuarioParam);
        }
        String asunto = request.getParameter("asunto");
        String informacion = request.getParameter("information");
        
        try {
            Connection conn = ConexionBD.getConnection();
            String sql = "INSERT INTO PA_BienesPorUsuario (FK_Usuario, FK_Bien, asunto, informacion, fechaObservacion) VALUES (?, 1, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, usuario);
            statement.setString(2, asunto);
            statement.setString(3, informacion);
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // Fecha actual
            statement.executeUpdate();

            System.out.println("Se ha insertado con éxito la observación");
            response.sendRedirect("homef.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al agregar la observación: " + e.getMessage());
            request.getRequestDispatcher("agregarobservacion.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}