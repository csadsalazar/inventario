package controllers;

import utils.ConexionBD;
import java.io.IOException;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AgregarObservacion")
public class AgregarObservacion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int usuario = Integer.parseInt(request.getParameter("usuario"));
        int bien = Integer.parseInt(request.getParameter("bien"));
        String asunto = request.getParameter("asunto");
        String informacion = request.getParameter("informacion");
        //String fechaObservacion = request.getParameter("fechaObservacion");

        try{
        Connection conn = ConexionBD.getConnection();
        String sql = "INSERT INTO PA_BienesPorUsuario (FK_Usuario, FK_Bien, asunto, informacion, fechaObservacion) VALUES (?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, usuario);
            statement.setInt(2, bien);
            statement.setString(3, asunto);
            statement.setString(4, informacion);
            //statement.setDate(5, fechaObservacion);
            statement.executeUpdate();

            System.out.println("Se ha insertado con exitola observacion");    
            request.getRequestDispatcher("homef.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al agregar el bien: " + e.getMessage());
            request.getRequestDispatcher("agregarobservacion.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
