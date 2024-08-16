package controllers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Dependency;
import models.Object;
import models.User;
import utils.ConnectionBD;

public class ObjectsEditMasive { 

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null; 
        try {
            conn = ConnectionBD.getConnection();
            List<Dependency> dependencias = ListDependencies.getDependencies();
            request.setAttribute("dependencia", dependencias);
            
            // Obtener el username desde la sesión
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
        
            // Verificar si el username está presente en la sesión
            if (username == null) {
                // Manejar el caso donde el username no está en la sesión (por ejemplo, redirigir a la página de inicio de sesión)
                request.setAttribute("error", "La sesión ha expirado. Por favor, inicia sesión nuevamente.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }
        
            // Obtener el ID del usuario usando el username
            int idUsuarioAdmin = 0;
            try {
                idUsuarioAdmin = UserController.getUserIdByUsername(username);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                // Manejar el error de manera apropiada
                request.setAttribute("error", "Error al obtener el ID del usuario: " + e.getMessage());
                request.getRequestDispatcher("managementobjects.jsp").forward(request, response);
                return;
            }
            
            request.setAttribute("userIdAdmin", idUsuarioAdmin);
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

    public static List<Object> getObjects(String[] ids) throws SQLException, ClassNotFoundException {
        List<Object> objectList = new ArrayList<>();
        Connection conn = ConnectionBD.getConnection();
        StringBuilder query = new StringBuilder("SELECT * FROM ADMINISTRATIVA.AL_INV.MA_Bien WHERE PK_Codigo IN (");

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
            obj.setState(rs.getString("estado"));
            obj.setCondition(rs.getString("condicion"));
            User user = ListUsers.getUsersById(rs.getInt("FK_Usuario"));
            obj.setUser(user);
            User admin = ListUsers.getUsersById(rs.getInt("FK_UsuarioAdmin"));
            obj.setAdmin(admin);
            Dependency dependencia = ListDependencies.getDependencyById(rs.getInt("FK_Dependencia"));
            obj.setPK_idDependency(dependencia);
            objectList.add(obj);
        }
        rs.close();
        stmt.close();
        conn.close();
        return objectList;
    }

    public static void updateObjects(HttpServletRequest request, String[] ids, String name, String description, String observation, String state, String dependency, String user, String condition, String admin) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionBD.getConnection();
        StringBuilder query = new StringBuilder("UPDATE ADMINISTRATIVA.AL_INV.MA_Bien SET ");
        
        // Construir la consulta SQL para los campos a actualizar
        boolean hasName = (name != null && !name.trim().isEmpty());
        boolean hasDescription = (description != null && !description.trim().isEmpty());
        boolean hasObservation = (observation != null && !observation.trim().isEmpty());
        boolean hasState = (state != null && !state.trim().isEmpty() && !state.equals(""));
        boolean hasCondition = (condition != null && !condition.trim().isEmpty() && !condition.equals(""));
        boolean hasDependency = (dependency != null && !dependency.trim().isEmpty() && !dependency.equals(""));
        boolean hasUser = (user != null && !user.trim().isEmpty());
        boolean hasAdmin = (admin != null && !admin.trim().isEmpty());
    
        // Solo agregamos los campos a la consulta si tienen valores válidos
        if (hasName) query.append("nombre = ?, ");
        if (hasDescription) query.append("descripcion = ?, ");
        if (hasObservation) query.append("observacionAdmin = ?, ");
        if (hasState) query.append("estado = ?, ");
        if (hasCondition) query.append("condicion = ?, ");
        if (hasDependency) query.append("FK_Dependencia = ?, ");
        if (hasUser) query.append("FK_Usuario = ?, ");
        if (hasAdmin) query.append("FK_UsuarioAdmin = ?, ");

        query.append("fechaAdmin = ?, ");

        // Eliminar la última coma y espacio
        if (query.length() > 0) {
            query.setLength(query.length() - 2);
        } else {
            // Si no hay campos para actualizar, no hay nada que hacer
            conn.close();
            return;
        }
        
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
        if (hasObservation) stmt.setString(index++, observation);
        if (hasState) stmt.setString(index++, state); 
        if (hasCondition) stmt.setString(index++, condition);
        if (hasDependency) stmt.setString(index++, dependency);
        if (hasUser) {
            // Aquí obtienes el ID del usuario usando el nombre de usuario
            int userId = UserController.getUserIdByUsername(user);
            stmt.setInt(index++, userId);
        }  
        if (hasAdmin) {
            // Obtener el ID del administrador desde el parámetro
            int adminId = Integer.parseInt(admin);
            stmt.setInt(index++, adminId);
        }
        stmt.setTimestamp(index++, new Timestamp(System.currentTimeMillis()));
        // Establecer los parámetros para los IDs de los objetos
        for (int i = 0; i < ids.length; i++) {
            stmt.setLong(index++, Long.parseLong(ids[i]));
        }
        stmt.executeUpdate();
        stmt.close();
        conn.close();

        List<Object> updatedObjects = getObjects(ids);
    
        for (Object obj : updatedObjects) {
            String userName = UserController.getUserNameById(obj.getUser().getPK_idUser());
            String userEmail = UserController.getUserEmailById(obj.getUser().getPK_idUser());
            EmailSender emailSender = new EmailSender();
            String subject = "Estado de bienes actualizados";
            StringBuilder body = new StringBuilder("<html><body>");
            body.append("<h1>Estimado/a ").append(userName).append(",</h1>");
            body.append("<p>El siguiente bien ha sido actualizado:</p>");
            body.append("<p><strong>Código:</strong> ").append(obj.getCode()).append("</p>");
            body.append("<p><strong>Nombre:</strong> ").append(obj.getName()).append("</p>");
            body.append("<p><strong>Descripción:</strong> ").append(obj.getDescription()).append("</p>");
            body.append("<p><strong>Valor:</strong> ").append(obj.getValue()).append("</p>");
            body.append("<p><strong>Dependencia:</strong> ").append(obj.getPK_idDependency().getDependencyname()).append("</p>");
            body.append("<p><strong>Estado:</strong> ").append(obj.getState()).append("</p>");
            body.append("<p><strong>Condición:</strong> ").append(obj.getCondition()).append("</p>");
            body.append("<p><strong>Observación:</strong> ").append(obj.getObservation()).append("</p>");
            body.append("</body></html>");
            emailSender.sendEmail(userEmail, subject, body.toString());
        }
    }
}
 