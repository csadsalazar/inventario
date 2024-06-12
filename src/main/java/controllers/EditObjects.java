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

@WebServlet("/EditObjects")
public class EditObjects extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros del formulario de edición
        String[] objetosSeleccionados = request.getParameterValues("objetosSeleccionados");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        long valor = Long.parseLong(request.getParameter("valor"));
        String estado = request.getParameter("estado");

        if (objetosSeleccionados != null && objetosSeleccionados.length > 0) {
            Connection conn = null;
            PreparedStatement stmt = null;
            try {
                conn = ConnectionBD.getConnection();
                // Preparar la consulta SQL para actualizar los objetos seleccionados
                String sql = "UPDATE MA_Bien SET nombre=?, descripcion=?, valor=?, estado=? WHERE PK_Codigo=?";
                stmt = conn.prepareStatement(sql);

                // Iterar sobre los objetos seleccionados y ejecutar la actualización para cada uno
                for (String codigo : objetosSeleccionados) {
                    stmt.setString(1, nombre);
                    stmt.setString(2, descripcion);
                    stmt.setLong(3, valor);
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

