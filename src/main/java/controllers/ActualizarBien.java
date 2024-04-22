package controllers;

import utils.ConexionBD;
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
import models.Dependencia;
import models.Bien;

@WebServlet("/ActualizarBien")
public class ActualizarBien extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Connection conn = null;
    try { 
        // Obtener el código del bien a actualizar
        long codigo = Long.parseLong(request.getParameter("codigo"));

        // Establecer la conexión y realizar la consulta para obtener los datos del bien
        conn = ConexionBD.getConnection();
        String sql = "SELECT * FROM MA_Bienes WHERE PK_Codigo=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, codigo);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Obtener los datos del bien de la consulta
            long codigoBien = resultSet.getLong("PK_Codigo");
            String nombre = resultSet.getString("nombre");
            String descripcion = resultSet.getString("descripcion");
            long valor = resultSet.getLong("valor");
            int idDependencia = resultSet.getInt("FK_Dependencia");

            // Crear un objeto bien y establecer sus propiedades
            Bien bien = new Bien();
            bien.setCodigo(codigoBien);
            bien.setNombre(nombre);
            bien.setDescripcion(descripcion);
            bien.setValor(valor);
            // Obtener la dependencia asociada al bien
            Dependencia dependencia = ListarDependencias.obtenerDependenciaPorId(idDependencia);
            request.setAttribute("dependenciaBien", dependencia);
            // Pasar el objeto bien al JSP como un atributo de solicitud
            request.setAttribute("bien", bien);
            // Redirigir al formulario de edición
            request.getRequestDispatcher("editarbien.jsp").forward(request, response);
        } else {
            // Manejar el caso donde no se encuentra el bien
            request.setAttribute("error", "El bien con el código " + codigo + " no existe");
            request.getRequestDispatcher("gestionbienes.jsp").forward(request, response);
        }
    } catch (NumberFormatException e) {
        // Manejar la excepción de formato incorrecto de número
        e.printStackTrace();
        request.setAttribute("error", "Formato de código incorrecto");
        request.getRequestDispatcher("gestionbienes.jsp").forward(request, response);
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        // Manejar el error, por ejemplo, redirigiendo a una página de error
        request.setAttribute("error", "Error al obtener los datos del bien: " + e.getMessage());
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

    long codigo = Long.parseLong(request.getParameter("codigo"));
    String nombre = request.getParameter("nombre");
    String descripcion = request.getParameter("descripcion");
    long valor = Long.parseLong(request.getParameter("valor"));
    String usuario = request.getParameter("usuario");
    int dependenciaId = Integer.parseInt(request.getParameter("dependencia"));
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
            statement.setLong(4, valor);
            statement.setInt(5, dependenciaId); 
            statement.setString(6, estado);
            statement.setLong(7, codigo);
            statement.executeUpdate();
            System.out.println("Se ha actualizado con éxito");
            
            // Redirigir después de la actualización
            response.sendRedirect("gestionbienes.jsp");

        } catch (NumberFormatException e) {
            // Manejar la excepción de formato incorrecto de número
            e.printStackTrace();
            request.setAttribute("error", "Formato de código incorrecto");
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
