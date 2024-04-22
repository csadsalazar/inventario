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
import models.Administrador;

@WebServlet("/ActualizarAdmin")
public class ActualizarAdmin extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Connection conn = null;
    try {
        // Obtener el código del admin a actualizar
        int codigo = Integer.parseInt(request.getParameter("codigo"));

        // Establecer la conexión y realizar la consulta para obtener los datos del bien
        conn = ConexionBD.getConnection();
        String sql = "SELECT * FROM MA_Administradores WHERE PK_idAdministrador=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, codigo);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Obtener los datos del admin de la consulta
            int codigoAdmin = resultSet.getInt("PK_idAdministrador");
            String usuario = resultSet.getString("usuario");
            String estado = resultSet.getString("estado");
           
            // Crear un objeto admin y establecer sus propiedades 
            Administrador administrador = new Administrador();
            administrador.setCodigo(codigoAdmin);
            administrador.setUsuario(usuario);
            administrador.setEstado(estado);
            // Pasar el objeto bien al JSP como un atributo de solicitud
            request.setAttribute("administrador", administrador);
            // Redirigir al formulario de edición
            request.getRequestDispatcher("editaradmin.jsp").forward(request, response);
        } else {
            // Manejar el caso donde no se encuentra el bien
            request.setAttribute("error", "El admin con el código " + codigo + " no existe");
            request.getRequestDispatcher("gestionadministradores.jsp").forward(request, response);
        }
    } catch (NumberFormatException e) {
        // Manejar la excepción de formato incorrecto de número
        e.printStackTrace();
        request.setAttribute("error", "Formato de código incorrecto");
        request.getRequestDispatcher("gestionbienes.jsp").forward(request, response);
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        // Manejar el error, por ejemplo, redirigiendo a una página de error
        request.setAttribute("error", "Error al obtener los datos del admin: " + e.getMessage());
        request.getRequestDispatcher("gestionadministradores.jsp").forward(request, response);
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
    String estado = request.getParameter("estado");
     
        try {
            // Establecer la conexión y realizar la actualización en la base de datos
            Connection conn = ConexionBD.getConnection();
            String sql = "UPDATE MA_Administradores SET estado=? WHERE PK_idAdministrador=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, estado);
            statement.setInt(2, codigo);
            statement.executeUpdate();
            System.out.println("Se ha actualizado con éxito");
            
            // Redirigir después de la actualización
            response.sendRedirect("gestionadministradores.jsp");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Formato de código incorrecto");
            request.getRequestDispatcher("editaradmin.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al actualizar el admin: " + e.getMessage());
            request.getRequestDispatcher("editaradmin.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    } 
    }

