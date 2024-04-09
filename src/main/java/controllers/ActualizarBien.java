package controllers;

import utils.ConexionBD;
import models.Bien;
import models.Usuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ActualizarBien")
public class ActualizarBien extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros del formulario
        int placa = Integer.parseInt(request.getParameter("placa"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        int valor = Integer.parseInt(request.getParameter("valor"));
        String funcionario = request.getParameter("funcionario");
        String ubicacion = request.getParameter("ubicacion");
        String estado = request.getParameter("estado");
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        // Obtener más parámetros según sea necesario

        try {
            // Establecer la conexión y realizar la actualización en la base de datos
            Connection conn = ConexionBD.getConnection();
            String sql = "UPDATE MA_Bienes SET placa=?, nombre=?, descripcion=?, valor=?, funcionario=?, ubicacion=?, estado=? WHERE PK_Codigo=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, placa);
            statement.setString(2, nombre);
            statement.setString(3, descripcion);
            statement.setInt(4, valor);
            statement.setString(5, funcionario);
            statement.setString(6, ubicacion);
            statement.setString(7, estado);
            statement.setInt(8, codigo);
            // Establecer más parámetros según sea necesario
            statement.executeUpdate();

            // Obtener el objeto bien actualizado desde la base de datos
            sql = "SELECT * FROM MA_Bienes WHERE PK_Codigo=?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, codigo);
            ResultSet resultSet = statement.executeQuery();
            Bien bien = new Bien(); // Inicializar fuera del bloque if
            try {
                // Obtener el objeto bien actualizado desde la base de datos
                if (resultSet.next()) {
                    // Llenar el objeto bien con los datos de la base de datos
                    bien.setCodigo(resultSet.getInt("PK_Codigo"));
                    bien.setPlaca(resultSet.getInt("placa"));
                    bien.setNombre(resultSet.getString("nombre"));
                    bien.setDescripcion(resultSet.getString("descripcion"));
                    bien.setValor(resultSet.getInt("valor"));
                    Usuario usuario = new Usuario();
                    usuario.setUsuario(resultSet.getString("funcionario"));
                    bien.setUsuario(usuario);                
                    bien.setUbicacion(resultSet.getString("ubicacion"));
                    bien.setEstado(resultSet.getString("estado"));
                } else {
                    // Manejar el caso en el que no hay resultados en resultSet
                    // Por ejemplo, redireccionar a una página de error
                    response.sendRedirect("editarbien.jsp");
                    return; // Salir del método para evitar más procesamiento
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejar la excepción adecuadamente, por ejemplo, redireccionar a una página de error
                response.sendRedirect("editarbien.jsp");
            }
             // Establecer el objeto bien como atributo de solicitud y enviar la solicitud al JSP editarbien.jsp
                request.setAttribute("bien", bien);
                request.getRequestDispatcher("editarbien.jsp").forward(request, response);

                statement.close();
                conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("editarbien.jsp");
            return; // Salir del método para evitar más procesamiento
        }
    }
}
