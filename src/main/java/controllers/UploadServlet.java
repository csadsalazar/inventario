package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import utils.ConexionBD;

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

            response.sendRedirect("gestionbienes.jsp"); // Redirigir a página de éxito
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("cargueexcel.jsp"); // Redirigir a página de error
        }
    }

    private void processExcel(InputStream fileContent) throws IOException, SQLException, ClassNotFoundException {
        try (Connection conn = ConexionBD.getConnection();
             HSSFWorkbook workbook = new HSSFWorkbook(fileContent)) { // Usar HSSFWorkbook para archivos en formato OLE2
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (org.apache.poi.ss.usermodel.Cell cell : row) {
                    int rowNum = row.getRowNum();
                    int colNum = cell.getColumnIndex();
                    System.out.println("Reading cell at row: " + rowNum + ", column: " + colNum);
                    continue; // Ignorar la primera fila (encabezados)
                }
    
                long codigo = 0;
                if (row.getCell(0) != null) {
                    if (row.getCell(0).getCellType() == CellType.NUMERIC) {
                        codigo = (int) row.getCell(0).getNumericCellValue();
                    } else if (row.getCell(0).getCellType() == CellType.STRING) {
                        // Si el código está representado como una cadena en la celda, puedes convertirlo a entero
                        codigo = Integer.parseInt(row.getCell(0).getStringCellValue());
                    }
                }     
    
                String nombre = "";
                if (row.getCell(1) != null) {
                    if (row.getCell(1).getCellType() == CellType.NUMERIC) {
                        // Si la celda es numérica, obtén su valor como cadena y concaténalo al nombre
                        double numericValue = row.getCell(1).getNumericCellValue();
                        nombre = "Num" + String.valueOf((int) numericValue); // Por ejemplo, puedes agregar "Num" al principio
                    } else if (row.getCell(1).getCellType() == CellType.STRING) {
                        // Si la celda ya es una cadena, obtén su valor directamente
                        nombre = row.getCell(1).getStringCellValue();
                    }
                }                
    
                int placa = 0;
                if (row.getCell(2) != null) {
                    if (row.getCell(2).getCellType() == CellType.NUMERIC) {
                        placa = (int) row.getCell(2).getNumericCellValue();
                    } else if (row.getCell(2).getCellType() == CellType.STRING) {
                        try {
                            placa = Integer.parseInt(row.getCell(2).getStringCellValue());
                        } catch (NumberFormatException e) {
                            // Manejar el error de conversión de cadena a entero
                            e.printStackTrace();
                        }
                    }
                }
    
                String descripcion = "";
                if (row.getCell(3) != null) {
                    descripcion = row.getCell(3).getStringCellValue();
                }
    
                String valorStr = "";
                if (row.getCell(4) != null) {
                    if (row.getCell(4).getCellType() == CellType.NUMERIC) {
                        valorStr = String.valueOf((long) row.getCell(4).getNumericCellValue());
                    } else if (row.getCell(4).getCellType() == CellType.STRING) {
                        valorStr = row.getCell(4).getStringCellValue();
                    }
                }
                long valor = 0;
                try {
                    valor = Long.parseLong(valorStr);
                } catch (NumberFormatException e) {
                    // Manejar el error de conversión de cadena a long
                    e.printStackTrace();
                }
    
                String nombreUsuario = "";
                int usuarioId = -1; // Valor predeterminado para el caso en que no se encuentre el usuario
                if (row.getCell(5) != null) {
                    nombreUsuario = row.getCell(5).getStringCellValue(); // Obtener nombre del usuario
                    // Obtener el ID del usuario desde la base de datos
                    usuarioId = getUserId(conn, nombreUsuario);
                }

                String nombreDependencia = "";
                int dependenciaId = -1; // Valor predeterminado para el caso en que no se encuentre el usuario
                if (row.getCell(5) != null) {
                    nombreDependencia = row.getCell(5).getStringCellValue(); // Obtener nombre del usuario
                    // Obtener el ID de la dependencia desde la base de datos
                    dependenciaId = getDependenciaId(conn, nombreDependencia);
                }

                String estado = "";
                if (row.getCell(7) != null) {
                    estado = row.getCell(7).getStringCellValue();
                }
        
                // Insertar datos en la base de datos
                String sql = "INSERT INTO MA_Bienes (PK_Codigo, nombre, placa, descripcion, valor, FK_Usuario, FK_Dependencia, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setLong(1, codigo);
                    pstmt.setString(2, nombre);
                    pstmt.setInt(3, placa);
                    pstmt.setString(4, descripcion);
                    pstmt.setLong(5, valor);
                    pstmt.setInt(6, usuarioId);
                    pstmt.setInt(7, dependenciaId);
                    pstmt.setString(8, estado);
    
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    // Manejar la excepción SQLException según sea necesario
                    e.printStackTrace();
                }
            }
        }
    }
    private void processCSV(InputStream fileContent) throws IOException, SQLException, ClassNotFoundException, CsvException {
        try (Connection conn = ConexionBD.getConnection();
                CSVReader reader = new CSVReader(new InputStreamReader(fileContent))) {
            List<String[]> records = reader.readAll();
            for (String[] record : records) {
                if (reader.getLinesRead() == 1) {
                    continue; // Ignorar la primera fila (encabezados)
                }

                long codigo = Long.parseLong(record[0]);
                String nombre = record[1];
                int placa = Integer.parseInt(record[2]);
                String descripcion = record[3];
                long valor = Long.parseLong(record[4]);
                String nombreUsuario = record[5]; // Obtener nombre del usuario
                String nombreDependencia = record[6]; // Obtener nombre de la dependencia
                String estado = record[7];

                int usuarioId = getUserId(conn, nombreUsuario); // Obtener ID del usuario
                int dependenciaId = getDependenciaId(conn, nombreDependencia); // Obtener ID de la dependencia

                // Insertar datos en la base de datos
                String sql = "INSERT INTO MA_Bienes (PK_Codigo, nombre, placa, descripcion, valor, FK_Usuario, FK_Dependencia, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setLong(1, codigo);
                    pstmt.setString(2, nombre);
                    pstmt.setInt(3, placa);
                    pstmt.setString(4, descripcion);
                    pstmt.setLong(5, valor);
                    pstmt.setInt(6, usuarioId);
                    pstmt.setInt(7, dependenciaId);
                    pstmt.setString(8, estado);

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
        // Si no se encuentra el usuario, puedes manejarlo como desees (lanzar una excepción, devolver un valor predeterminado, etc.)
        return -1; // Valor predeterminado para indicar que no se encontró el usuario
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
        // Si no se encuentra la dependencia, puedes manejarlo como desees (lanzar una excepción, devolver un valor predeterminado, etc.)
        return -1; // Valor predeterminado para indicar que no se encontró la dependencia
    }
}
