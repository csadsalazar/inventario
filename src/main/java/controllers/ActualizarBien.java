package controllers;

import utils.ConexionBD;
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

import models.Dependencia;

@WebServlet("/ActualizarBien")
public class ActualizarBien extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null; 
        try {
            conn = ConexionBD.getConnection();
            List<Dependencia> dependencias = ListarDependencias.obtenerDependencias();
            request.setAttribute("dependencia", dependencias);
            request.getRequestDispatcher("agregarbien.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Manejar el error, por ejemplo, redirigiendo a una página de error
            request.setAttribute("error", "Error al obtener las dependencias: " + e.getMessage());
            request.getRequestDispatcher("gestionbienes.jsp").forward(request, response);
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

        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        int valor = Integer.parseInt(request.getParameter("valor"));
        String usuario = request.getParameter("usuario");
        int dependencia =  Integer.parseInt(request.getParameter("dependencia"));
        String estado = request.getParameter("estado");

        // Verificar si el usuario existe antes de actualizar el bien
        if (UsuarioController.usuarioExiste(usuario)) {
            try {
                // Obtener el ID del usuario
                int idUsuario = UsuarioController.obtenerIdUsuario(usuario);

                // Establecer la conexión y realizar la actualización en la base de datos
                Connection conn = ConexionBD.getConnection();
                String sql = "UPDATE MA_Bienes SET FK_Usuario=?, nombre=?, descripcion=?, valor=?, FK_Dependencia=?, estado=? WHERE PK_Codigo=?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, idUsuario);
                statement.setString(2, nombre);
                statement.setString(3, descripcion);
                statement.setInt(4, valor);
                statement.setInt(5, dependencia);
                statement.setString(6, estado);
                statement.setInt(7, codigo);
                statement.executeUpdate();

                System.out.println("Se ha actualizado con éxito");
                request.getRequestDispatcher("gestionbienes.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                // Manejar la excepción de formato incorrecto de número
                e.printStackTrace();
                request.getRequestDispatcher("editarbien.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al actualizar el bien: " + e.getMessage());
                request.getRequestDispatcher("editarbien.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // Manejar el caso donde el usuario no existe
            request.setAttribute("error", "El usuario proporcionado no existe");
            request.getRequestDispatcher("editarbien.jsp").forward(request, response);
        }
    }
}
