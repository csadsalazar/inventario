package controllers;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import utils.ConnectionBD;
 
@WebServlet("/UploadServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) // 10 MB
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file"); // Nombre del input file en el formulario HTML
        try (InputStream fileContent = filePart.getInputStream()) {
if (filePart.getContentType().equals("application/vnd.ms-excel")
                    || filePart.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                processExcel(fileContent, request, response); // Procesar archivo Excel
            } else if (filePart.getContentType().equals("text/csv")) {
                processCSV(fileContent, request, response); // Procesar archivo CSV
            }
            response.sendRedirect("managementobjects.jsp"); // Redirigir a página de éxito
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("uploadfile.jsp"); // Redirigir a página de error
        }
    }
 
    private void processExcel(InputStream fileContent, HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException, ServletException {
        try (Connection conn = ConnectionBD.getConnection();
             HSSFWorkbook workbook = new HSSFWorkbook(fileContent)) {
            Sheet sheet = workbook.getSheetAt(0);
            String sql = "INSERT INTO ADMINISTRATIVA.AL_INV.MA_Bien (PK_Codigo, nombre, placa, descripcion, valor, FK_Usuario, FK_UsuarioAdmin, FK_Dependencia, estado, fecha, fechaAdmin, condicion) VALUES (?,?,?,?,?,?,?,?,'No reportado',?,?,'Activo')";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) {
                        continue; // Ignorar la primera fila (encabezados)
                    }
 
                    // Validar campos nulos
                    if (row.getCell(0) == null || row.getCell(1) == null || row.getCell(3) == null || row.getCell(9) == null || row.getCell(52) == null || row.getCell(55) == null || row.getCell(56) == null) {
                        // Omitir fila si hay campos nulos
                        continue;
                    }
 
                    long codigo;
                    try {
                        codigo = (long) row.getCell(0).getNumericCellValue();
                    } catch (IllegalStateException e) {
                        // Si la celda contiene texto en lugar de un valor numérico, intenta obtener el valor como texto
                        codigo = Long.parseLong(row.getCell(0).getStringCellValue());
                    }
 
                    String nombre = row.getCell(1).getStringCellValue();
 
                    int placa;
                    try {
                        placa = (int) row.getCell(3).getNumericCellValue();
                    } catch (IllegalStateException e) {
                        // Si la celda contiene texto en lugar de un valor numérico, intenta obtener el valor como texto
                        placa = Integer.parseInt(row.getCell(3).getStringCellValue());
                    }
 
                    long valor;
                    try {
                        valor = (long) row.getCell(9).getNumericCellValue();
                    } catch (IllegalStateException e) {
                        // Si la celda contiene texto en lugar de un valor numérico, intenta obtener el valor como texto
                        valor = Long.parseLong(row.getCell(9).getStringCellValue());
                    }
 
 
                    String descripcion = row.getCell(52).getStringCellValue();
                    String nombreUsuario = row.getCell(55).getStringCellValue();
                    String nombreDependencia = row.getCell(56).getStringCellValue();
 
                    // Validar códigos y placas repetidas
                    if (codigoExistente(conn, codigo) || placaExistente(conn, placa)) {
                        // Omitir fila si el código o la placa ya existen
                        continue;
                    }
 
                    // Obtener IDs de usuario y dependencia
                    int usuarioId = getUserId(conn, nombreUsuario); // Obtener ID del usuario
                    int dependenciaId = getDependenciaId(conn, nombreDependencia); // Obtener ID de la dependencia
 
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
                    int idUsuarioAdmin = UserController.getUserIdByUsername(username);
 
                    // Set values for the prepared statement
                    pstmt.setLong(1, codigo);
                    pstmt.setString(2, nombre);
                    pstmt.setInt(3, placa);
                    pstmt.setString(4, descripcion);
                    pstmt.setLong(5, valor);
                    pstmt.setInt(6, usuarioId);
                    pstmt.setInt(7, idUsuarioAdmin);
                    pstmt.setInt(8, dependenciaId);
                    pstmt.setTimestamp(9, new Timestamp(System.currentTimeMillis())); // Fecha actual
                    pstmt.setTimestamp(10, new Timestamp(System.currentTimeMillis())); // Fecha actual
 
                    // Add to batch
                    pstmt.addBatch();
                }
                // Execute batch
                pstmt.executeBatch();
            }
        }
    }

    private void processCSV(InputStream fileContent, HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException, CsvException, ServletException {
        try (Connection conn = ConnectionBD.getConnection();
             CSVReader reader = new CSVReader(new InputStreamReader(fileContent))) {
            String sql = "INSERT INTO ADMINISTRATIVA.AL_INV.MA_Bien (PK_Codigo, nombre, placa, descripcion, valor, FK_Usuario, FK_UsuarioAdmin, FK_Dependencia, estado, fecha, fechaAdmin, condicion) VALUES (?,?,?,?,?,?,?,?,'No reportado',?,?,'Activo')";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                reader.skip(1); // Saltar la primera fila (encabezados)
                String[] record;
                while ((record = reader.readNext()) != null) {
                    // Validar campos nulos
                    if (record.length < 57 || record[0] == null || record[1] == null || record[3] == null || record[9] == null || record[52] == null || record[55] == null || record[56] == null) {
                        // Omitir fila si hay campos nulos
                        continue;
                    }
 
                    long codigo = Long.parseLong(record[0]);
                    String nombre = record[1];
                    int placa = Integer.parseInt(record[3]);
                    long valor = Long.parseLong(record[9]);
                    String descripcion = record[52];
                    String nombreUsuario = record[55];
                    String nombreDependencia = record[56];
 
                    //  Validar códigos y placas repetidas
                    if (codigoExistente(conn, codigo) || placaExistente(conn, placa)) {
                        // Omitir fila si el código o la placa ya existen
                        continue;
                    }
 
                    // Obtener IDs de usuario y dependencia
                    int usuarioId = getUserId(conn, nombreUsuario); // Obtener ID del usuario
                    int dependenciaId = getDependenciaId(conn, nombreDependencia); // Obtener ID de la dependencia
 
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
                    int idUsuarioAdmin = UserController.getUserIdByUsername(username);
 
                    // Set values for the prepared statement
                    pstmt.setLong(1, codigo);
                    pstmt.setString(2, nombre);
                    pstmt.setInt(3, placa);
                    pstmt.setString(4, descripcion);
                    pstmt.setLong(5, valor);
                    pstmt.setInt(6, usuarioId);
                    pstmt.setInt(7, idUsuarioAdmin);
                    pstmt.setInt(8, dependenciaId);
                    pstmt.setTimestamp(9, new Timestamp(System.currentTimeMillis())); // Fecha actual
                    pstmt.setTimestamp(10, new Timestamp(System.currentTimeMillis())); // Fecha actual
 
                    // Add to batch
                    pstmt.addBatch();
                }
                // Execute batch
                pstmt.executeBatch();
            }
        }
    }
 

 
    private int getUserId(Connection conn, String nombreUsuario) throws SQLException {
        String sql = "SELECT PK_idUsuario FROM ADMINISTRATIVA.AL_INV.MA_Usuario WHERE nombre = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombreUsuario);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("PK_idUsuario");
                } else {
                    throw new SQLException("Usuario no encontrado: " + nombreUsuario);
                }
            }
        }
    }
 
    private int getDependenciaId(Connection conn, String nombreDependencia) throws SQLException {
        String sql = "SELECT PK_idDependencia FROM ADMINISTRATIVA.AL_INV.MA_Dependencia WHERE nombreDependencia = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombreDependencia);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("PK_idDependencia");
                } else {
                    throw new SQLException("Dependencia no encontrada: " + nombreDependencia);
                }
            }
        }
    }

    private boolean codigoExistente(Connection conn, long codigo) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM ADMINISTRATIVA.AL_INV.MA_Bien WHERE PK_Codigo = ?";
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
        String sql = "SELECT COUNT(*) AS count FROM ADMINISTRATIVA.AL_INV.MA_Bien WHERE placa = ?";
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