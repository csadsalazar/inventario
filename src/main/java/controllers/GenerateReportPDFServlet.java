package controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Object;
import models.User;
import models.Dependency;
import utils.ConnectionBD;

@WebServlet("/GenerateReportPDFServlet")
public class GenerateReportPDFServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int dependenciaId = Integer.parseInt(request.getParameter("dependencia"));
        List<Object> listaBienes =  new ArrayList<>();
        try {
            listaBienes = getObjectsByDependency(dependenciaId, dependenciaId);
            if (listaBienes != null && !listaBienes.isEmpty()) {
                PDFController pdfController = new PDFController();
                pdfController.generateFilePDF(listaBienes, response);
            } else {
                // Manejar el caso en el que no se encontraron bienes
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private List<Object> getObjectsByDependency(int dependenciaId, int usuarioId) throws ClassNotFoundException {
        try (Connection connection = ConnectionBD.getConnection()) {
            List<Object> bienes = new ArrayList<>();
            String sql = "SELECT * FROM MA_Bien WHERE FK_Dependencia = ? AND FK_Usuario = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, dependenciaId);
                statement.setInt(2, usuarioId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Object bien = new Object();
                        bien.setCodigo(resultSet.getLong("PK_Codigo"));
                        bien.setNombre(resultSet.getString("nombre"));
                        bien.setPlaca(resultSet.getInt("placa"));
                        User usuario = new User();
                        usuario.setPK_idUsuario(resultSet.getInt("FK_Usuario"));
                        usuario = getUserById(usuario.getPK_idUsuario());
                        bien.setUsuario(usuario);
                        bien.setDescripcion(resultSet.getString("descripcion"));
                        bien.setValor(resultSet.getLong("valor"));
                        bien.setEstado(resultSet.getString("estado"));
                        Dependency dependencia = ListDependencies.getDependencyById(resultSet.getInt("FK_Dependencia"));
                        bien.setDependencia(dependencia);
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
            String sql = "SELECT * FROM MA_Usuario WHERE PK_idUsuario = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, usuarioId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        User usuario = new User();
                        usuario.setPK_idUsuario(resultSet.getInt("PK_idUsuario"));
                        usuario.setUsuario(resultSet.getString("usuario"));
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