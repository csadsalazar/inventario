package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.ConexionBD;
import javax.servlet.annotation.WebServlet;

@WebServlet("/PieChartServlet")
public class PieChartServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        // Conectar a la base de datos y obtener los datos
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexionBD.getConnection();
            String query = "SELECT MA_Dependencias.nombreDependencia, " +
                    "COUNT(MA_Bienes.idBien) AS total_bienes, " +
                    "SUM(CASE WHEN MA_Bienes.estado = 'Reportado' THEN 1 ELSE 0 END) AS reportados " +
                    "FROM MA_Dependencias " +
                    "LEFT JOIN MA_Bienes ON MA_Dependencias.PK_idDependencia = MA_Bienes.FK_Dependencia " +
                    "GROUP BY MA_Dependencias.nombreDependencia";
            stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery();

            // Variables para el cálculo del porcentaje general
            int totalBienesTotales = 0;
            int totalBienesReportados = 0;

            // Crear un objeto JSON para almacenar los datos de la gráfica
            JSONArray dataset = new JSONArray();

            while (rs.next()) {
                JSONObject data = new JSONObject();
                String nombreDependencia = rs.getString("nombreDependencia");
                int totalBienes = rs.getInt("total_bienes");
                int bienesReportados = rs.getInt("reportados");
                totalBienesTotales += totalBienes;
                totalBienesReportados += bienesReportados;

                // Almacenar el nombre de la dependencia y su porcentaje de bienes reportados
                double porcentajeDependencia = totalBienes > 0 ? ((double) bienesReportados / totalBienes) * 100 : 0;
                data.put("nombreDependencia", nombreDependencia);
                data.put("porcentajeBienes", porcentajeDependencia);
                dataset.add(data);
            }

            // Calcular porcentaje general
            double porcentajeGeneral = totalBienesTotales > 0 ? ((double) totalBienesReportados / totalBienesTotales) * 100 : 0;

            // Crear objeto JSON para el porcentaje general
            JSONObject generalPercentage = new JSONObject();
            generalPercentage.put("nombreDependencia", "General");
            generalPercentage.put("porcentajeBienes", porcentajeGeneral);
            dataset.add(generalPercentage);

            // Escribir los datos JSON en la respuesta
            PrintWriter out = response.getWriter();
            out.print(dataset);
            out.flush();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
