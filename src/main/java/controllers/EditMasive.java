package controllers;

import utils.ConnectionBD;
import models.Dependency;
import models.Object;
import models.User;

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

@WebServlet("/EditMasive")
public class EditMasive extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener los IDs de los bienes seleccionados del formulario
        String[] selectedIds = request.getParameter("selectedIds").split(",");

        try {
            // Establecer la conexión a la base de datos
            Connection conn = ConnectionBD.getConnection();

            // Preparar la consulta para obtener y actualizar cada bien seleccionado
            String sql = "SELECT * FROM MA_Bien WHERE PK_Codigo=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            for (String id : selectedIds) {
                long codigo = Long.parseLong(id);
                statement.setLong(1, codigo);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {  
                    // Obtener los datos del bien de la consulta
                    Object bien = new Object();
                    bien.setCode(resultSet.getLong("PK_Codigo"));
                    bien.setDescription(request.getParameter("descripcion")); // Actualizar la descripción
                    bien.setObservation(request.getParameter("observacion")); // Actualizar la observación
                    bien.setState(resultSet.getString("estado"));

                    User user = new User();
                    user.setPK_idUser(resultSet.getInt("FK_Usuario"));
                    bien.setUser(user); 

                    Dependency dependency = new Dependency();
                    dependency.setPK_idDependency(resultSet.getInt("FK_Dependencia"));
                    bien.setPK_idDependency(dependency);

                    // Realizar la actualización en la base de datos
                    sql = "UPDATE MA_Bien SET descripcion=?, observacionAdmin=? WHERE PK_Codigo=?";
                    PreparedStatement updateStatement = conn.prepareStatement(sql);
                    updateStatement.setString(1, bien.getDescription());
                    updateStatement.setString(2, bien.getObservation());
                    updateStatement.setLong(3, bien.getCode());
                    updateStatement.executeUpdate();
                }
            }
            // Cerrar la conexión
            conn.close();
            // Redirigir después de la actualización
            response.sendRedirect("managementobjects.jsp");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Manejar errores de formato de número
            request.setAttribute("error", "Formato de código incorrecto");
            request.getRequestDispatcher("managementobjects.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Manejar otros errores de SQL o conexión
            request.setAttribute("error", "Error al actualizar los bienes: " + e.getMessage());
            request.getRequestDispatcher("managementobjects.jsp").forward(request, response);
        }
    }
}