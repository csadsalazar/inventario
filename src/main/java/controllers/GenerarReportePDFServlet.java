package controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Bien;
import utils.ConexionBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GenerarReportePDFServlet")
public class GenerarReportePDFServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int dependenciaId = Integer.parseInt(request.getParameter("dependencia"));
        List<Bien> listaBienes = new ArrayList<>();
        try {
            listaBienes = obtenerBienesPorDependencia(dependenciaId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PDFController pdfController = new PDFController();
        pdfController.generarArchivoPDF(listaBienes, response);
    }

    private List<Bien> obtenerBienesPorDependencia(int dependenciaId) throws ClassNotFoundException {
        List<Bien> bienes = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection()) {
            String sql = "SELECT * FROM MA_Bienes WHERE FK_Dependencia = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, dependenciaId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Bien bien = new Bien();
                        bien.setidBien(resultSet.getInt("idBien"));
                        bien.setCodigo(resultSet.getLong("PK_Codigo"));
                        bien.setNombre(resultSet.getString("nombre"));
                        bien.setPlaca(resultSet.getInt("placa"));
                        bien.setDescripcion(resultSet.getString("descripcion"));
                        bien.setValor(resultSet.getLong("valor"));
                        // Agrega más atributos según tu modelo
    
                        bienes.add(bien);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción
        }
        return bienes;
    }
}
    
