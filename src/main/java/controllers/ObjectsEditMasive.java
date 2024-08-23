package controllers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        
        if (hasName) query.append("nombre = ?, ");
        if (hasDescription) query.append("descripcion = ?, ");
        if (hasObservation) query.append("observacionAdmin = ?, ");
        if (hasState) query.append("estado = ?, ");
        if (hasCondition) query.append("condicion = ?, ");
        if (hasDependency) query.append("FK_Dependencia = ?, ");
        if (hasUser) query.append("FK_Usuario = ?, ");
        if (hasAdmin) query.append("FK_UsuarioAdmin = ?, ");
        
        query.append("fechaAdmin = ?, ");
        
        if (query.length() > 0) {
            query.setLength(query.length() - 2);
        } else {
            conn.close();
            return;
        }
        
        query.append(" WHERE PK_Codigo IN (");
        
        for (int i = 0; i < ids.length; i++) {
            query.append("?");
            if (i < ids.length - 1) {
                query.append(",");
            }
        }
        query.append(")");
        
        PreparedStatement stmt = conn.prepareStatement(query.toString());
        
        int index = 1;
        if (hasName) stmt.setString(index++, name);
        if (hasDescription) stmt.setString(index++, description);
        if (hasObservation) stmt.setString(index++, observation);
        if (hasState) stmt.setString(index++, state);
        if (hasCondition) stmt.setString(index++, condition);
        if (hasDependency) stmt.setString(index++, dependency);
        if (hasUser) {
            int userId = UserController.getUserIdByUsername(user);
            stmt.setInt(index++, userId);
        }
        if (hasAdmin) {
            int adminId = Integer.parseInt(admin);
            stmt.setInt(index++, adminId);
        }
        stmt.setTimestamp(index++, new Timestamp(System.currentTimeMillis()));
        for (int i = 0; i < ids.length; i++) {
            stmt.setLong(index++, Long.parseLong(ids[i]));
        } 
        stmt.executeUpdate();
        stmt.close();
        
        // Obtener todos los objetos actualizados
        List<Object> updatedObjects = getObjects(ids);
        
        // Crear un mapa para verificar si todos los bienes del usuario están reportados y activos
        Map<Integer, Boolean> userStatusMap = new HashMap<>();
        
        for (Object obj : updatedObjects) {
            int userId = obj.getUser().getPK_idUser();
            if (!userStatusMap.containsKey(userId)) {
                boolean allReportedAndActive = allItemsReportedAndActiveForUser(userId, conn);
                userStatusMap.put(userId, allReportedAndActive);
            }
        }
        
        // Enviar correos a los usuarios si todos sus bienes están reportados y activos
        EmailSender emailSender = new EmailSender();
        for (Map.Entry<Integer, Boolean> entry : userStatusMap.entrySet()) {
            int userId = entry.getKey();
            boolean allReportedAndActive = entry.getValue();
            
            if (allReportedAndActive) {
                String userName = UserController.getUserNameById(userId);
                String userEmail = UserController.getUserEmailById(userId);
                String subject = "Todos tus bienes están reportados y activos";
                StringBuilder body = new StringBuilder("<html><body>");
                body.append("<h1>Estimado/a ").append(userName).append(",</h1>");
                body.append("<p>Todos los bienes asignados a usted están en estado Reportado y Activos.</p>");
                body.append("<p><strong>Saludos,</strong></p>");
                body.append("<p><strong>Oficina Administrativa y Grupo de almacen general.</strong></p>");
                body.append("</body></html>");
                emailSender.sendEmail(userEmail, subject, body.toString());
            }
        }
        
        // Enviar correos individuales con los detalles de cada bien actualizado
        for (Object obj : updatedObjects) {
            String userName = UserController.getUserNameById(obj.getUser().getPK_idUser());
            String userEmail = UserController.getUserEmailById(obj.getUser().getPK_idUser());
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
            body.append("<p><strong>Observación:</strong> ").append(obj.getObservation()).append("</p><br>");
            body.append("<p><strong>Saludos,</strong></p>");
            body.append("<p><strong>Oficina Administrativa y Grupo de almacen general</strong></p>");
            body.append("</body></html>");
            emailSender.sendEmail(userEmail, subject, body.toString());
        }
        
        conn.close();
    }

    private static boolean allItemsReportedAndActiveForUser(int userId, Connection conn) throws SQLException {
        String totalItemsQuery = "SELECT COUNT(*) AS total_bienes FROM ADMINISTRATIVA.AL_INV.MA_Bien WHERE FK_Usuario = ?";
        String reportedActiveItemsQuery = "SELECT COUNT(*) AS reportados_activos FROM ADMINISTRATIVA.AL_INV.MA_Bien WHERE FK_Usuario = ? AND estado = 'Reportado' AND condicion = 'Activo'";
        
        PreparedStatement totalItemsStmt = conn.prepareStatement(totalItemsQuery);
        totalItemsStmt.setInt(1, userId);
        ResultSet totalItemsRs = totalItemsStmt.executeQuery();
        totalItemsRs.next();
        int totalItems = totalItemsRs.getInt("total_bienes");
        totalItemsRs.close();
        totalItemsStmt.close();
        
        PreparedStatement reportedActiveItemsStmt = conn.prepareStatement(reportedActiveItemsQuery);
        reportedActiveItemsStmt.setInt(1, userId);
        ResultSet reportedActiveItemsRs = reportedActiveItemsStmt.executeQuery();
        reportedActiveItemsRs.next();
        int reportedActiveItems = reportedActiveItemsRs.getInt("reportados_activos");
        reportedActiveItemsRs.close();
        reportedActiveItemsStmt.close();
        
        return totalItems == reportedActiveItems;
    }
}