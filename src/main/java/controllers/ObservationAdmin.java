package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ConnectionBD;

@WebServlet("/ObservationAdmin")
public class ObservationAdmin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer idadmin = (Integer) session.getAttribute("idadmin"); // Utiliza la misma clave que en el servlet Login
        // Recuperar el código del bien y la observación del formulario
        long codigoBien = Long.parseLong(request.getParameter("codigoBien"));
        String observacion = request.getParameter("informacion");
        // Obtener la fecha actual
        java.sql.Date fechaObservacion = new java.sql.Date(new Date().getTime());
        
        // Insertar la observación en la base de datos
        try {
            Connection conn = ConnectionBD.getConnection();
            String sql = "INSERT INTO PA_BienPorUsuario (FK_Admin, FK_Bien, informacion, fechaObservacion) VALUES (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idadmin);
            statement.setLong(2, codigoBien);
            statement.setString(3, observacion);
            statement.setDate(4, fechaObservacion);
            int rowsAffected = statement.executeUpdate();

            System.out.println("Se ha agregado la observacion del usuario" +idadmin + "y" +codigoBien);
            conn.close();
             
            if (rowsAffected > 0) {
                // Redirigir de vuelta a la página de detalles del bien si la inserción fue exitosa
                response.sendRedirect("seeobjecta.jsp?codigo=" + codigoBien);
            } else {
                // Si no se insertaron filas, mostrar un mensaje de error
                response.getWriter().println("Error: No se pudo insertar la observación.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error al procesar la solicitud: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}