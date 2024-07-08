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
import jxl.write.WriteException; // Agregar esta importación
import models.Object;
import models.User;
import models.Dependency;
import utils.ConnectionBD;

@WebServlet("/GenerateReportExcelServlet")
public class GenerateReportExcelServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int dependenciaId = Integer.parseInt(request.getParameter("dependencia"));
        List<Object> listaBienes =  new ArrayList<>();
        try {
            listaBienes = getObjectsByDependency(dependenciaId, dependenciaId);
            if (listaBienes != null && !listaBienes.isEmpty()) {
                ExcelController excelController = new ExcelController();
                excelController.generateFileExcel(listaBienes, response);
            } else {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch (ClassNotFoundException | WriteException e) {
            e.printStackTrace();
        }
    }
    
    private List<Object> getObjectsByDependency(int dependenciaId, int usuarioId) throws ClassNotFoundException {
        try (Connection connection = ConnectionBD.getConnection()) {
            List<Object> bienes = new ArrayList<>();
            String sql = "SELECT * FROM MA_Bien WHERE FK_Dependencia = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, dependenciaId);
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
            String sql = "SELECT * FROM MA_Usuario WHERE PK_idUsuario = ?";
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