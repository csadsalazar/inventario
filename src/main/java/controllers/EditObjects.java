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

@WebServlet("/EditObjects")
public class EditObjects extends HttpServlet {
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

        String usuario = request.getParameter("usuario");
        int dependenciaId = Integer.parseInt(request.getParameter("dependencia")); // Obtener el ID de la dependencia
        String descripcion = request.getParameter("descripcion");
        String estado = request.getParameter("estado");

        // Obtener los códigos de los bienes seleccionados
        String[] objetosSeleccionados = request.getParameterValues("selectedObjects");

        if (objetosSeleccionados != null && objetosSeleccionados.length > 0) {
            Connection conn = null;
            PreparedStatement stmt = null;
            if (UserController.userExists(usuario)) { 
            try {
              
                int idUsuario = UserController.getUserId(usuario);
                conn = ConnectionBD.getConnection();
                // Preparar la consulta SQL para actualizar los objetos seleccionados
                String sql = "UPDATE MA_Bien SET FK_Usuario=?, FK_Dependencia=?, descripcion=?, estado=? WHERE PK_Codigo=?";
                stmt = conn.prepareStatement(sql);

                // Iterar sobre los objetos seleccionados y ejecutar la actualización para cada uno
                for (String codigo : objetosSeleccionados) {
                    stmt.setInt(1, idUsuario);
                    stmt.setInt(2, dependenciaId);
                    stmt.setString(3, descripcion);
                    stmt.setString(4, estado);
                    stmt.setLong(5, Long.parseLong(codigo));
                    stmt.addBatch(); // Agregar la consulta al lote de ejecución
                }

                // Ejecutar el lote de consultas de actualización
                int[] resultados = stmt.executeBatch();

                // Verificar si la actualización se realizó correctamente para todos los objetos seleccionados
                for (int resultado : resultados) {
                    if (resultado == PreparedStatement.EXECUTE_FAILED) {
                        // Manejar el caso de fallo en la actualización
                        throw new SQLException("Error al actualizar uno o más objetos");
                    }
                }

                // Redirigir después de la actualización exitosa
                response.sendRedirect("managementobjects.jsp");

            } catch (SQLException | ClassNotFoundException e) {
                // Manejar las excepciones
                e.printStackTrace();
                // Aquí puedes redirigir a una página de error o mostrar un mensaje de error en la página
                response.sendRedirect("managementobjects.jsp");
            } finally {
                // Cerrar la conexión y el statement en el bloque finally para asegurarse de que se cierre correctamente
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Manejar el caso donde no se seleccionaron objetos para editar
            response.sendRedirect("managementobjects.jsp"); // Puedes redirigir a la página de gestión de objetos
        }
    }
}
}