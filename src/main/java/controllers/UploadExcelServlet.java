/*
package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.mysql.cj.result.Row;

import jxl.Sheet;
import jxl.Workbook;
import utils.ConexionBD;

@WebServlet("/UploadExcelServlet")
public class UploadExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Part filePart = request.getPart("file");
            InputStream fileContent = filePart.getInputStream();

            Workbook workbook = WorkbookFactory.create(fileContent);
            Sheet sheet = workbook.getSheetAt(0); // Suponiendo que los datos están en la primera hoja

            Connection connection = ConexionBD.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO MA_Bienes (PK_Codigo, nombre, placa, descripcion, valor, FK_Usuario, FK_Dependencia, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );

            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Saltar la primera fila (encabezados)
                    continue;
                }

                statement.setLong(1, (long) row.getCell(0).getNumericCellValue()); // PK_Codigo
                statement.setString(2, row.getCell(1).getStringCellValue()); // nombre
                statement.setInt(3, (int) row.getCell(2).getNumericCellValue()); // placa
                statement.setString(4, row.getCell(3).getStringCellValue()); // descripcion
                statement.setLong(5, (long) row.getCell(4).getNumericCellValue()); // valor
                statement.setInt(6, (int) row.getCell(5).getNumericCellValue()); // FK_Usuario
                statement.setInt(7, (int) row.getCell(6).getNumericCellValue()); // FK_Dependencia
                statement.setString(8, row.getCell(7).getStringCellValue()); // estado

                statement.addBatch();
            }

            statement.executeBatch();

            connection.close();

            response.getWriter().println("Datos cargados exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar los datos");
        }
    }
}
*/