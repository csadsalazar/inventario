package controllers;

import utils.ConexionBD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ActualizarBien")
public class ActualizarBien extends HttpServlet {
    //private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String placa = request.getParameter("placa");
        String nombre = request.getParameter("nombre");
        String ubicacion = request.getParameter("ubicacion");
        String descripcion = request.getParameter("descripcion");
        int valor = Integer.parseInt(request.getParameter("valor"));

        // Actualizar la información del bien en la base de datos
        try {
            Connection conn = ConexionBD.getConnection();
            String query = "UPDATE MA_Bienes SET placa=?, nombre=?, ubicacion=?, descripcion=?, valor=? WHERE PK_Codigo=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, placa);
            ps.setString(2, nombre);
            ps.setString(3, ubicacion);
            ps.setString(4, descripcion);
            ps.setInt(5, valor);
            ps.setInt(6, codigo);
            int rowsAffected = ps.executeUpdate();
            ps.close();
            conn.close();

            if (rowsAffected > 0) {
                // Redirigir a la página de gestión de bienes con un mensaje de éxito
                response.sendRedirect("gestionbienes.jsp?mensaje=El+bien+se+actualiz%C3%B3+exitosamente");
            } else {
                // Redirigir a la página de gestión de bienes con un mensaje de error
                response.sendRedirect("gestionbienes.jsp?mensaje=Error+al+actualizar+el+bien");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar errores de la base de datos
            response.sendRedirect("gestionbienes.jsp?mensaje=Error+de+base+de+datos");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
