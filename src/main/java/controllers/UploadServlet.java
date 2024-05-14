package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import utils.ConnectionBD;

@WebServlet("/UploadServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) // 10 MB
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("file"); // Nombre del input file en el formulario HTML

        try (InputStream fileContent = filePart.getInputStream()) {
            if (filePart.getContentType().equals("application/vnd.ms-excel")
                    || filePart.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                processExcel(fileContent); // Procesar archivo Excel
            } else if (filePart.getContentType().equals("text/csv")) {
                processCSV(fileContent); // Procesar archivo CSV
            }

            response.sendRedirect("managementobjects.jsp"); // Redirigir a página de éxito
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("uploadfile.jsp"); // Redirigir a página de error
        }
    }

    private void processExcel(InputStream fileContent) throws IOException, SQLException, ClassNotFoundException {
        try (Connection conn = ConnectionBD.getConnection();
             HSSFWorkbook workbook = new HSSFWorkbook(fileContent)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0 ) {
                    continue; // Ignorar la primera fila (encabezados)
                }

                // Validar campos nulos
                if (row.getCell(0) == null || row.getCell(1) == null || row.getCell(2) == null || row.getCell(3) == null
                        || row.getCell(4) == null || row.getCell(5) == null || row.getCell(6) == null || row.getCell(7) == null) {
                    // Omitir fila si hay campos nulos
                    continue;
                }

                long codigo = (long) row.getCell(0).getNumericCellValue();
                String nombre = row.getCell(1).getStringCellValue();
                int placa = (int) row.getCell(2).getNumericCellValue();
                String descripcion = row.getCell(3).getStringCellValue();
                long valor = (long) row.getCell(4).getNumericCellValue();
                String nombreUsuario = row.getCell(5).getStringCellValue();
                String nombreDependencia = row.getCell(6).getStringCellValue();

                // Validar códigos y placas repetidas
                if (codigoExistente(conn, codigo) || placaExistente(conn, placa))  {
                    // Omitir fila si el código o la placa ya existen
                    continue;
                }

                int usuarioId = getUserId(conn, nombreUsuario); // Obtener ID del usuario
                int dependenciaId = getDependenciaId(conn, nombreDependencia); // Obtener ID de la dependencia

                // Insertar datos en la base de datos
                String sql = "INSERT INTO MA_Bienes (PK_Codigo, nombre, placa, descripcion, valor, FK_Usuario, FK_Dependencia, estado, fecha) VALUES (?, ?, ?, ?, ?, ?, ?, No reportado, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setLong(1, codigo);
                    pstmt.setString(2, nombre);
                    pstmt.setInt(3, placa);
                    pstmt.setString(4, descripcion);
                    pstmt.setLong(5, valor);
                    pstmt.setInt(6, usuarioId);
                    pstmt.setInt(7, dependenciaId);
                    pstmt.setTimestamp(8, new Timestamp(System.currentTimeMillis())); // Fecha actual

                    pstmt.executeUpdate();
                }
            }
        }
    }

    private void processCSV(InputStream fileContent) throws IOException, SQLException, ClassNotFoundException, CsvException {
        try (Connection conn = ConnectionBD.getConnection();
                CSVReader reader = new CSVReader(new InputStreamReader(fileContent))) {
            reader.skip(1); // Saltar la primera fila (encabezados)
            String[] record;
            while ((record = reader.readNext()) != null) {
                long codigo = Long.parseLong(record[0]);
                String nombre = record[1];
                int placa = Integer.parseInt(record[2]);
                String descripcion = record[3];
                long valor = Long.parseLong(record[4]);
                String nombreUsuario = record[5]; // Obtener nombre del usuario
                String nombreDependencia = record[6]; // Obtener nombre de la dependencia

                // Validar campos nulos
                if (record.length < 8 || record[0] == null || record[1] == null || record[2] == null || record[3] == null
                        || record[4] == null || record[5] == null || record[6] == null || record[7] == null) {
                    // Omitir fila si hay campos nulos
                    continue;
                }

                // Validar códigos y placas repetidas
                if (codigoExistente(conn, codigo) || placaExistente(conn, placa)) {
                    // Omitir fila si el código o la placa ya existen
                    continue;
                }

                int usuarioId = getUserId(conn, nombreUsuario); // Obtener ID del usuario
                int dependenciaId = getDependenciaId(conn, nombreDependencia); // Obtener ID de la dependencia

                // Insertar datos en la base de datos
                String sql = "INSERT INTO MA_Bienes (PK_Codigo, nombre, placa, descripcion, valor, FK_Usuario, FK_Dependencia, estado, fecha) VALUES (?, ?, ?, ?, ?, ?, ?, No reportado, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setLong(1, codigo);
                    pstmt.setString(2, nombre);
                    pstmt.setInt(3, placa);
                    pstmt.setString(4, descripcion);
                    pstmt.setLong(5, valor);
                    pstmt.setInt(6, usuarioId);
                    pstmt.setInt(7, dependenciaId);

                    pstmt.executeUpdate();
                }
            }
        }
    }

    private int getUserId(Connection conn, String nombreUsuario) throws SQLException {
        String sql = "SELECT PK_idUsuario FROM MA_Usuarios WHERE nombre = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombreUsuario);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("PK_idUsuario");
                }
            }
        }
        // Si no se encuentra el usuario, puedes manejarlo como desees
        return -1;
    }

    private int getDependenciaId(Connection conn, String nombreDependencia) throws SQLException {
        String sql = "SELECT PK_idDependencia FROM MA_Dependencias WHERE nombreDependencia = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombreDependencia);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("PK_idDependencia");
                }
            }
        }
        // Si no se encuentra la dependencia, puedes manejarlo como desees
        return -1;
    }

    private boolean codigoExistente(Connection conn, long codigo) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM MA_Bienes WHERE PK_Codigo = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count") > 0;
                }
            }
        }
        return false;
    }

    private boolean placaExistente(Connection conn, int placa) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM MA_Bienes WHERE placa = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, placa);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count") > 0;
                }
            }
        }
        return false;
    }
}