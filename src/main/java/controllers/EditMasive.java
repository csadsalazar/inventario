package controllers;

import utils.ConnectionBD;
import models.Dependency;
import models.Object;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditObject")
public class EditMasive extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long codigo = Long.parseLong(request.getParameter("selectedIds"));
        System.out.println(codigo);
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        long valor = Long.parseLong(request.getParameter("valor"));
        String usuario = request.getParameter("usuario");
        int dependenciaId = Integer.parseInt(request.getParameter("dependencia"));
        String estado = request.getParameter("estado");
        String observacion = request.getParameter("observacion");

        // Verificar si el usuario existe antes de actualizar el bien
        if (UserController.userExists(usuario)) { 
            try {
                // Obtener el ID del usuario
                int idUsuario = UserController.getUserId(usuario);
                // Establecer la conexión y realizar la actualización en la base de datos
                Connection conn = ConnectionBD.getConnection();
                String sql = "UPDATE MA_Bien SET FK_Usuario=?, nombre=?, descripcion=?, valor=?, FK_Dependencia=?, estado=?, observacionAdmin=? WHERE PK_Codigo=?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, idUsuario);
                statement.setString(2, nombre);
                statement.setString(3, descripcion);
                statement.setLong(4, valor);
                statement.setInt(5, dependenciaId);
                statement.setString(6, estado);
                statement.setString(7, observacion);
                statement.setLong(8, codigo);
                statement.executeUpdate();
                System.out.println("Se ha actualizado con éxito");
                // Redirigir después de la actualización
                response.sendRedirect("managementobjects.jsp");

            } catch (NumberFormatException e) {
                // Manejar la excepción de formato incorrecto de número
                e.printStackTrace();
                System.out.println(codigo);
                System.out.println(usuario);
                request.setAttribute("error", "Formato de código incorrecto");
                request.getRequestDispatcher("editobject.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al actualizar el bien: " + e.getMessage());
                request.getRequestDispatcher("editobject.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // Manejar el caso donde el usuario no existe
            request.setAttribute("error", "El usuario proporcionado no existe");
            request.getRequestDispatcher("editobject.jsp").forward(request, response);
        }
    }
}  