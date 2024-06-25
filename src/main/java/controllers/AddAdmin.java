package controllers;

import utils.ConnectionBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddAdmin")
public class AddAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String perfil = request.getParameter("perfil");
    String usuario = request.getParameter("username");
    if (UserController.userExists(usuario)){ 
        try {
            // Establecer la conexión y realizar la inserción en la base de datos
            Connection conn = ConnectionBD.getConnection();
            String sql = "UPDATE MA_Usuario SET FK_Perfil=? WHERE usuario=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, perfil);
            statement.setString(2, usuario);
            statement.executeUpdate();
            response.setContentType("text/plain"); 
            response.setCharacterEncoding("UTF-8");
            System.out.println("Se ha insertado con éxito");
            request.getRequestDispatcher("managementadmins.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                // Manejar la excepción de formato incorrecto de número
                e.printStackTrace();
                request.getRequestDispatcher("addadmin.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al agregar el admin: " + e.getMessage());
                request.getRequestDispatcher("addadmin.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            // Manejar el caso donde el usuario no existe
            request.setAttribute("error", "El usuario proporcionado no existe.");
            request.getRequestDispatcher("addadmin.jsp").forward(request, response);
        }
    }
}