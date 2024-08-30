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
        String idUsuarioParam = request.getParameter("idUsuario");
        Integer idUsuario = null; // Inicializar como nulo
        //Integer idBien = null; // Inicializar como nulo
   
        // Verificar si el parámetro no es nulo
        if (idUsuarioParam != null && !idUsuarioParam.isEmpty()) {
            try {
                // Convertir el parámetro a un entero
                idUsuario = Integer.parseInt(idUsuarioParam);
            } catch (NumberFormatException e) {
                // Manejar la excepción si el parámetro no se puede convertir a un entero
                e.printStackTrace();
                // Puedes enviar una respuesta de error o redirigir a una página de error
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'idUsuario' no es un número válido");
                return; // Salir del método
            }
        }

        String codigo = request.getParameter("codigoBien");
        String asunto = request.getParameter("placaBien");
        String informacion = request.getParameter("informacion");

        try {
            Connection conn = ConnectionBD.getConnection();
            String sql = "INSERT INTO ADMINISTRATIVA.AL_INV.PA_BienPorUsuario (FK_Usuario, FK_Bien, asunto, informacion, fechaObservacion) VALUES (?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setObject(1, idUsuario);
            statement.setObject(2, codigo);
            statement.setString(3, asunto);
            statement.setString(4, informacion);
            statement.setTimestamp(5, new Timestamp(System.currentTimeMillis())); // Fecha actual
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