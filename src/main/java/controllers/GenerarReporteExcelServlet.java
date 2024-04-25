package controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;  
import jxl.write.WriteException;
import models.Bien;
import models.Dependencia;
import models.Usuario;
import utils.ConexionBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GenerarReporteExcelServlet")
public class GenerarReporteExcelServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int dependenciaId = Integer.parseInt(request.getParameter("dependencia"));
        List<Bien> listaBienes =  new ArrayList<>();
        try {
            listaBienes = obtenerBienesPorDependencia(dependenciaId, dependenciaId);
            if (listaBienes != null && !listaBienes.isEmpty()) {
                ExcelController excelController = new ExcelController();
                excelController.generarArchivoExcel(listaBienes, response);
            } else {
                // Manejar el caso en el que no se encontraron bienes
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch (ClassNotFoundException | WriteException e) {
            e.printStackTrace();
        }
    }
    

    private List<Bien> obtenerBienesPorDependencia(int dependenciaId, int usuarioId) throws ClassNotFoundException {
        try (Connection connection = ConexionBD.getConnection()) {
            List<Bien> bienes = new ArrayList<>();
            String sql = "SELECT * FROM MA_Bienes WHERE FK_Dependencia = ? AND FK_Usuario = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, dependenciaId);
                statement.setInt(2, usuarioId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Bien bien = new Bien();
                        bien.setCodigo(resultSet.getLong("PK_Codigo"));
                        bien.setNombre(resultSet.getString("nombre"));
                        bien.setPlaca(resultSet.getInt("placa"));
                        Usuario usuario = new Usuario();
                        usuario.setPK_idUsuario(resultSet.getInt("FK_Usuario"));
                        usuario = obtenerUsuarioPorId(usuario.getPK_idUsuario());
                        bien.setUsuario(usuario);
                        bien.setDescripcion(resultSet.getString("descripcion"));
                        bien.setValor(resultSet.getLong("valor"));
                        bien.setEstado(resultSet.getString("estado"));
                        Dependencia dependencia = ListarDependencias.obtenerDependenciaPorId(resultSet.getInt("FK_Dependencia"));
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
    
    private Usuario obtenerUsuarioPorId(int usuarioId) throws ClassNotFoundException {
        try (Connection connection = ConexionBD.getConnection()) {
            String sql = "SELECT * FROM MA_Usuarios WHERE PK_idUsuario = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, usuarioId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Usuario usuario = new Usuario();
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
