package controllers;

import models.User;
import utils.ConnectionBD;
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

@WebServlet("/EditAdmin")
public class EditAdmin extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        try {
            int idAdministrador = Integer.parseInt(request.getParameter("codigo"));
            conn = ConnectionBD.getConnection();
            String sql = "SELECT * FROM MA_Usuario WHERE PK_idUsuario=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idAdministrador);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int codigoAdministrador = resultSet.getInt("PK_idUsuario");
                String usuario = resultSet.getString("usuario");
                String perfil = resultSet.getString("perfil");
                User administrador = new User(codigoAdministrador, codigoAdministrador, perfil, usuario, null, null, null, null);
                request.setAttribute("administrador", administrador);
                request.getRequestDispatcher("editadmin.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "El administrador con el ID " + idAdministrador + " no existe");
                request.getRequestDispatcher("managementadmins.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Formato de ID incorrecto");
            request.getRequestDispatcher("managementadmins.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al obtener los datos del administrador: " + e.getMessage());
            request.getRequestDispatcher("managementadmins.jsp").forward(request, response);
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
            int perfil = Integer.parseInt(request.getParameter("perfil"));
    
            conn = ConnectionBD.getConnection();
            String sql = "UPDATE MA_Usuario SET FK_Perfil=? WHERE PK_idUsuario=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, perfil);
            statement.setInt(2, idAdministrador); 
    
            int filasActualizadas = statement.executeUpdate(); 
            if (filasActualizadas > 0) {
                // La actualización fue exitosa, redireccionar a alguna página de éxito
                response.sendRedirect("managementadmins.jsp");
            } else {
                // No se pudo actualizar el administrador, mostrar algún mensaje de error
                request.setAttribute("error", "No se pudo actualizar el administrador con el ID " + idAdministrador);
                request.getRequestDispatcher("editadmin.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Formato de ID incorrecto");
            request.getRequestDispatcher("managementadmins.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al actualizar el administrador: " + e.getMessage());
            request.getRequestDispatcher("editadmin.jsp").forward(request, response);
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