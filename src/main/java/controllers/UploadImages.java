package controllers;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException; 
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import utils.ConnectionBD;

@WebServlet("/UploadImages")
@MultipartConfig 
public class UploadImages extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String STORAGE_BASE_URL = "https://storagepermlabesinvima.blob.core.windows.net/";
    private static final String CONTAINER_NAME = "inventariopersonalizado";
    private static final String SAS_TOKEN = "sv=2022-11-02&ss=bfqt&srt=sco&sp=rwlacupitfx&se=2029-08-01T03:19:53Z&st=2024-07-31T19:19:53Z&spr=https&sig=SXXVMTTVpVE2jfuWXemUCeU8kKnoaBpZ%2B02C4iIWBI8%3D";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idBien = Integer.parseInt(request.getParameter("idBien"));

        // Leer imágenes existentes
        String imagenuno = request.getParameter("imagenuno_existing");
        String imagendos = request.getParameter("imagendos_existing");
        String imagentres = request.getParameter("imagentres_existing");

        // Procesar archivos de imagen y actualizar solo si se proporcionan nuevas imágenes
        String nuevaImagenUno = handleFileUpload(request, "imagenuno");
        String nuevaImagenDos = handleFileUpload(request, "imagendos");
        String nuevaImagenTres = handleFileUpload(request, "imagentres");

        // Si se proporcionan nuevas imágenes, actualizar las variables de imagen correspondientes
        if (nuevaImagenUno != null) {
            imagenuno = nuevaImagenUno;
        }
        if (nuevaImagenDos != null) {
            imagendos = nuevaImagenDos;
        }
        if (nuevaImagenTres != null) {
            imagentres = nuevaImagenTres; 
        }
 
        // Actualizar la base de datos
        try (Connection conn = ConnectionBD.getConnection()) {
            String updateQuery = "UPDATE ADMINISTRATIVA.AL_INV.Bien SET imagenuno = ?, imagendos = ?, imagentres = ? WHERE PK_Codigo = ?";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, imagenuno);
            pstmt.setString(2, imagendos);
            pstmt.setString(3, imagentres);
            pstmt.setInt(4, idBien);
            pstmt.executeUpdate();
            response.sendRedirect("homef.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al actualizar los datos en la base de datos.");
            request.getRequestDispatcher("homef.jsp").forward(request, response);
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error de clase no encontrada.");
            request.getRequestDispatcher("homef.jsp").forward(request, response);
            return;
        }
    }

    String handleFileUpload(HttpServletRequest request, String fieldName) throws IOException, ServletException {
        Part filePart = request.getPart(fieldName);
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = extractFileName(filePart);
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());

            // Subir el archivo a Azure Blob Storage
            BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                    .endpoint(STORAGE_BASE_URL)
                    .sasToken(SAS_TOKEN)
                    .buildClient();
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(CONTAINER_NAME);
            BlobClient blobClient = containerClient.getBlobClient(encodedFileName);

            try (InputStream inputStream = filePart.getInputStream()) {
                blobClient.upload(inputStream, filePart.getSize(), true);
            }

            // Generar la URL pública con SAS token
            return blobClient.getBlobUrl() + "?" + SAS_TOKEN;
        }
        return null;
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1).replace("\"", "");
            }
        }
        return "";
    }
}