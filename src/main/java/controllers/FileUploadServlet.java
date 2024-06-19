package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.azure.storage.file.share.ShareFileClient;
import com.azure.storage.file.share.ShareFileClientBuilder;

@WebServlet("/uploadFiles")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    
    @SuppressWarnings("deprecation")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Part filePart1 = request.getPart("file1");
            Part filePart2 = request.getPart("file2");
            
            // Aquí puedes guardar los archivos en tu sistema de archivos o almacenamiento en la nube
            // Ejemplo para guardar en Azure Blob Storage o File Share
            String storageConnectionString = "DefaultEndpointsProtocol=https;AccountName='storagepermlabesinvima';AccountKey='eGhazE8xw9tVHQTnF0Swy24ygb/CBkdKKhb+iEVPuowkRPOX3kFvpbA0qTx3ekN5eRW0K/yguHH/+AStRQj/Gg==';EndpointSuffix=core.windows.net";
            String shareName = "permisoslaborales";
            String dirName = "uploads"; // Directorio donde guardar los archivos
            
            ShareFileClient fileClient1 = new ShareFileClientBuilder()
                    .connectionString(storageConnectionString)
                    .shareName(shareName)
                    .resourcePath(dirName + "/" + getFileName(filePart1))
                    .buildFileClient();
            
            fileClient1.create(filePart1.getSize());
            fileClient1.upload(filePart1.getInputStream(), filePart1.getSize());
            
            ShareFileClient fileClient2 = new ShareFileClientBuilder()
                    .connectionString(storageConnectionString)
                    .shareName(shareName)
                    .resourcePath(dirName + "/" + getFileName(filePart2))
                    .buildFileClient();
            
            fileClient2.create(filePart2.getSize());
            fileClient2.upload(filePart2.getInputStream(), filePart2.getSize());
            
            // Respondemos con un mensaje de éxito (opcional)
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\": \"Archivos cargados exitosamente\"}");
        } catch (Exception e) {
            // Manejo de errores
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
    
    // Método auxiliar para obtener el nombre del archivo desde Part
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }
}
