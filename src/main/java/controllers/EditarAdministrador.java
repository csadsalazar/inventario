package controllers;

import models.Administrador;
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

@WebServlet("/EditarAdministrador")
public class EditarAdministrador extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        try {
            int idAdministrador = Integer.parseInt(request.getParameter("id"));
            conn = ConexionBD.getConnection();
            String sql = "SELECT * FROM MA_Administradores WHERE PK_idAdministrador=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idAdministrador);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int codigoAdministrador = resultSet.getInt("PK_idAdministrador");
                String usuario = resultSet.getString("usuario");
                String estado = resultSet.getString("estado");
                Administrador administrador = new Administrador(codigoAdministrador, usuario, estado);
                request.setAttribute("administrador", administrador);
                request.getRequestDispatcher("editaradmin.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "El administrador con el ID " + idAdministrador + " no existe");
                request.getRequestDispatcher("gestionadmin.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Formato de ID incorrecto");
            request.getRequestDispatcher("gestionadmin.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al obtener los datos del administrador: " + e.getMessage());
            request.getRequestDispatcher("gestionadmin.jsp").forward(request, response);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        try {
            int idAdministrador = Integer.parseInt(request.getParameter("codigo"));
            String estado = request.getParameter("estado");
    
            conn = ConexionBD.getConnection();
            String sql = "UPDATE MA_Administradores SET estado=? WHERE PK_idAdministrador=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, estado);
            statement.setInt(2, idAdministrador);
    
            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                // La actualización fue exitosa, redireccionar a alguna página de éxito
                response.sendRedirect("gestionadmin.jsp");
            } else {
                // No se pudo actualizar el administrador, mostrar algún mensaje de error
                request.setAttribute("error", "No se pudo actualizar el administrador con el ID " + idAdministrador);
                request.getRequestDispatcher("editaradmin.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Formato de ID incorrecto");
            request.getRequestDispatcher("gestionadmin.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al actualizar el administrador: " + e.getMessage());
            request.getRequestDispatcher("editaradmin.jsp").forward(request, response);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
