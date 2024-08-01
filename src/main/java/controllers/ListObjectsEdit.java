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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListObjectsEdit {

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

    public static List<Object> getObjects(String[] ids, HttpServletRequest request) throws SQLException, ClassNotFoundException {

        // Obtener el username desde la sesión
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
 
        // Obtener el ID del usuario usando el username
        int idUsuarioAdmin = 0;
        try {
            idUsuarioAdmin = UserController.getUserIdByUsername(username);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        List<Object> objectList = new ArrayList<>();
        Connection conn = ConnectionBD.getConnection();
        StringBuilder query = new StringBuilder("SELECT * FROM MA_Bien WHERE PK_Codigo IN (");

        // Construir la consulta SQL con los IDs
        for (int i = 0; i < ids.length; i++) {
            query.append("?");
            if (i < ids.length - 1) {
                query.append(",");
            }
        }
        query.append(")");

        PreparedStatement stmt = conn.prepareStatement(query.toString());

        // Establecer los parámetros en el PreparedStatement
        for (int i = 0; i < ids.length; i++) {
            stmt.setLong(i + 1, Long.parseLong(ids[i]));
        }

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Object obj = new Object();
            obj.setCode(rs.getLong("PK_Codigo"));
            obj.setName(rs.getString("nombre"));
            obj.setDescription(rs.getString("descripcion"));
            obj.setValue(rs.getLong("valor"));
            obj.setObservation(rs.getString("observacionAdmin"));
            User admin = new User();
            admin.setPK_idUser(rs.getInt("FK_UsuarioAdmin"));
            obj.setAdmin(admin);  
            User user = new User();
            user.setPK_idUser(rs.getInt("FK_Usuario"));
            obj.setUser(user);
            Dependency dependency = new Dependency();
            dependency.setPK_idDependency(rs.getInt("PK_idDependency"));
            obj.setPK_idDependency(dependency);
            objectList.add(obj);
        }
        rs.close();
        stmt.close();
        conn.close();
        return objectList;
    }

    public static void updateObjects(String[] ids, String name, String description) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionBD.getConnection();
        StringBuilder query = new StringBuilder("UPDATE MA_Bien SET ");

        // Construir la consulta SQL para los campos a actualizar
        boolean hasName = (name != null && !name.trim().isEmpty());
        boolean hasDescription = (description != null && !description.trim().isEmpty());

        if (hasName) query.append("nombre = ?, ");
        if (hasDescription) query.append("descripcion = ?, ");

        // Eliminar la última coma y espacio
        query.setLength(query.length() - 2);
        query.append(" WHERE PK_Codigo IN (");

        // Construir la lista de parámetros
        for (int i = 0; i < ids.length; i++) {
            query.append("?");
            if (i < ids.length - 1) {
                query.append(",");
            }
        }
        query.append(")");
        PreparedStatement stmt = conn.prepareStatement(query.toString());

        // Establecer los parámetros en el PreparedStatement
        int index = 1;
        if (hasName) stmt.setString(index++, name);
        if (hasDescription) stmt.setString(index++, description);

        for (int i = 0; i < ids.length; i++) {
            stmt.setLong(index++, Long.parseLong(ids[i]));
        }
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
}