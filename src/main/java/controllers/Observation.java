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
import utils.ConnectionBD;

@WebServlet("/Observation")
public class Observation extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar el código del bien y la observación del formulario
        long codigoBien = Long.parseLong(request.getParameter("codigoBien"));
        String observacion = request.getParameter("informacion");
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));       
        // Obtener la fecha actual
        java.sql.Date fechaObservacion = new java.sql.Date(new Date().getTime());
        
        // Insertar la observación en la base de datos
        try {
            Connection conn = ConnectionBD.getConnection();
            String sql = "INSERT INTO ADMINISTRATIVA.AL_INV.PA_BienPorUsuario (FK_Usuario, FK_Bien, asunto, informacion, fechaObservacion) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idUsuario);
            statement.setLong(2, codigoBien);
            statement.setString(3, "Bien:" + codigoBien);
            statement.setString(4, observacion);
            statement.setDate(5, fechaObservacion);
            int rowsAffected = statement.executeUpdate();

            System.out.println("Se ha agregado la observacion del usuario" +idUsuario + "y" +codigoBien);
            conn.close();
            
            if (rowsAffected > 0) {
                // Redirigir de vuelta a la página de detalles del bien si la inserción fue exitosa
                response.sendRedirect("seeobjectf.jsp?codigo=" + codigoBien);
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