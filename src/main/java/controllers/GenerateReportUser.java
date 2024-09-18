package controllers;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jxl.write.WriteException;
import models.Object;
import models.User;
import models.Dependency;
import utils.ConnectionBD;

@WebServlet("/GenerateReportUser")
public class GenerateReportUser extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        int idUsuario = 0;
        try {
            idUsuario = UserController.getUserIdByUsername(username);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener el ID del usuario.");
            return;
        }

        List<Object> listaBienes = new ArrayList<>();
        try {
            listaBienes = getObjectsByUser(idUsuario);
            if (listaBienes != null && !listaBienes.isEmpty()) {
                ExcelController excelController = new ExcelController();
                excelController.generateFileExcel(listaBienes, response);
            } else {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch (ClassNotFoundException | WriteException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar el archivo Excel.");
        }
    }

    private List<Object> getObjectsByUser(int usuarioId) throws ClassNotFoundException {
        try (Connection connection = ConnectionBD.getConnection()) {
            List<Object> bienes = new ArrayList<>();
            String sql = "SELECT * FROM ADMINISTRATIVA.AL_INV.Bien WHERE condicion = 'Activo' AND  FK_Usuario = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, usuarioId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Object bien = new Object();
                        bien.setCode(resultSet.getLong("PK_Codigo"));
                        bien.setName(resultSet.getString("nombre"));
                        bien.setPlate(resultSet.getInt("placa"));
                        User usuario = new User();
                        usuario.setPK_idUser(resultSet.getInt("FK_Usuario"));
                        usuario = getUserById(usuario.getPK_idUser());
                        bien.setUser(usuario);
                        bien.setDescription(resultSet.getString("descripcion"));
                        bien.setValue(resultSet.getLong("valor"));
                        bien.setState(resultSet.getString("estado"));
                        bien.setObservation(resultSet.getString("observacionAdmin"));
                        Dependency dependencia = ListDependencies.getDependencyById(resultSet.getInt("FK_Dependencia"));
                        bien.setPK_idDependency(dependencia);
                        bienes.add(bien);
                    }
                }
            }
            return bienes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User getUserById(int usuarioId) throws ClassNotFoundException {
        try (Connection connection = ConnectionBD.getConnection()) {
            String sql = "SELECT * FROM ADMINISTRATIVA.AL_INV.Usuario WHERE PK_idUsuario = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, usuarioId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        User usuario = new User();
                        usuario.setPK_idUser(resultSet.getInt("PK_idUsuario"));
                        usuario.setUser(resultSet.getString("usuario"));
                        return usuario;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}