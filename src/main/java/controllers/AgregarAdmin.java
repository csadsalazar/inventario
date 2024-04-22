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


@WebServlet("/AgregarAdmin")
public class AgregarAdmin extends HttpServlet {
        
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String usuario = request.getParameter("usuario");
    String estado = request.getParameter("estado");
    if (UsuarioController.usuarioExiste(usuario)){
        try {
            // Establecer la conexión y realizar la inserción en la base de datos
            Connection conn = ConexionBD.getConnection();
            String sql = "INSERT INTO MA_Administradores (usuario, estado) VALUES (?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, usuario);
            statement.setString(2, estado);
            statement.executeUpdate();
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            System.out.println("Se ha insertado con éxito");
            request.getRequestDispatcher("gestionadministradores.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                // Manejar la excepción de formato incorrecto de número
                e.printStackTrace();
                request.getRequestDispatcher("agregaradmin.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al agregar el admin: " + e.getMessage());
                request.getRequestDispatcher("agregaradmin.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            // Manejar el caso donde el usuario no existe
            request.setAttribute("error", "El usuario proporcionado no existe.");
            request.getRequestDispatcher("agregaradmin.jsp").forward(request, response);
        }
    }
}