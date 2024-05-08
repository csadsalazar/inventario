package controllers;

import utils.ConnectionBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Dependency;

@WebServlet("/AddObject")
public class AddObject extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null; 
        try {
            conn = ConnectionBD.getConnection();
            List<Dependency> dependencias = ListDependencies.getDependencies();
            request.setAttribute("dependencia", dependencias);
            request.getRequestDispatcher("addobject.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Manejar el error, por ejemplo, redirigiendo a una página de error
            request.setAttribute("error", "Error al obtener las dependencias: " + e.getMessage());
            request.getRequestDispatcher("managementobjects.jsp").forward(request, response);
        } finally {
            // Cerrar la conexión en el bloque finally para asegurarse de que se cierre correctamente
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    long codigo = Long.parseLong(request.getParameter("codigo"));
    int placa = Integer.parseInt(request.getParameter("placa"));
    String nombre = request.getParameter("nombre");
    String descripcion = request.getParameter("descripcion");
    long valor = Long.parseLong(request.getParameter("valor"));
    String usuario = request.getParameter("usuario");
    int dependenciaId = Integer.parseInt(request.getParameter("dependencia")); // Obtener el ID de la dependencia
    String estado = request.getParameter("estado");

    // Verificar si el usuario existe antes de agregar el bien
    if (UserController.userExists(usuario)) {
        try {
            // Obtener el ID del usuario
            int idUsuario = UserController.getUserId(usuario);

            // Establecer la conexión y realizar la inserción en la base de datos
            Connection conn = ConnectionBD.getConnection();
            String sql = "INSERT INTO MA_Bienes (PK_Codigo, placa, nombre, descripcion, valor, FK_Usuario, FK_Dependencia, estado) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, codigo);
            statement.setInt(2, placa);
            statement.setString(3, nombre);
            statement.setString(4, descripcion);
            statement.setLong(5, valor);
            statement.setInt(6, idUsuario);
            statement.setInt(7, dependenciaId);
            statement.setString(8, estado);
            statement.executeUpdate();
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            System.out.println("Se ha insertado con éxito"); 
            request.getRequestDispatcher("managementobjects.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                // Manejar la excepción de formato incorrecto de número
                e.printStackTrace();
                request.getRequestDispatcher("addobject.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al agregar el bien: " + e.getMessage());
                request.getRequestDispatcher("addobject.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // Manejar el caso donde el usuario no existe
            request.setAttribute("error", "El usuario proporcionado no existe");
            request.getRequestDispatcher("addobject.jsp").forward(request, response);
        }
    }
}