package controllers;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import models.Dependency;
import utils.ConnectionBD;

@WebServlet("/AddObject")
@MultipartConfig
public class AddObject extends HttpServlet { 
    private static final long serialVersionUID = 1L;
    private static final String STORAGE_BASE_URL = "https://storagepermlabesinvima.blob.core.windows.net/";
    private static final String CONTAINER_NAME = "inventariopersonalizado";
    private static final String SAS_TOKEN = "sv=2022-11-02&ss=bfqt&srt=sco&sp=rwlacupitfx&se=2029-08-01T03:19:53Z&st=2024-07-31T19:19:53Z&spr=https&sig=SXXVMTTVpVE2jfuWXemUCeU8kKnoaBpZ%2B02C4iIWBI8%3D";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null; 
        try {
            conn = ConnectionBD.getConnection();
            List<Dependency> dependencias = ListDependencies.getDependencies();
            request.setAttribute("dependencia", dependencias);
            request.getRequestDispatcher("addobject.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Manejar el error, por ejemplo, redirigiendo a una página de error
            request.setAttribute("error", "Error al obtener las dependencias: " + e.getMessage());
            request.getRequestDispatcher("managementobjects.jsp").forward(request, response);
        } finally {
            // Cerrar la conexión en el bloque finally para asegurarse de que se cierre correctamente
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } 
        }
    }
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        int idUsuarioAdmin = 0;
        try {
            idUsuarioAdmin = UserController.getUserIdByUsername(username);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        long codigo = Long.parseLong(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        long valor = Long.parseLong(request.getParameter("valor"));
        String usuario = request.getParameter("usuario");
        int dependenciaId = Integer.parseInt(request.getParameter("dependencia"));
        int placa = Integer.parseInt(request.getParameter("placa"));
        String estado = request.getParameter("estado");
        String observacion = request.getParameter("observacion");

        // Leer imágenes existentes
        String imagenuno = request.getParameter("imagenuno");
        String imagendos = request.getParameter("imagendos");
        String imagentres = request.getParameter("imagentres");


        // Verificar si el usuario existe antes de insertar el bien
        if (UserController.userExists(usuario)) {
            try {
                // Obtener el ID del usuario
                int idUsuario = UserController.getUserId(usuario);

                // Establecer la conexión y realizar la inserción en la base de datos
                Connection conn = ConnectionBD.getConnection();
                String sql = "INSERT INTO ADMINISTRATIVA.AL_INV.MA_Bien (FK_Usuario, PK_Codigo, placa, nombre, descripcion, valor, FK_Dependencia, estado, observacionAdmin, FK_UsuarioAdmin, imagenuno, imagendos, imagentres, fechaAdmin, fecha, condicion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Activo')";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, idUsuario);
                statement.setLong(2, codigo);
                statement.setLong(3, placa);
                statement.setString(4, nombre);
                statement.setString(5, descripcion);
                statement.setLong(6, valor);
                statement.setInt(7, dependenciaId);
                statement.setString(8, estado);
                statement.setString(9, observacion);
                statement.setInt(10, idUsuarioAdmin);
                statement.setString(11, imagenuno);
                statement.setString(12, imagendos);
                statement.setString(13, imagentres);
                statement.setTimestamp(14, new Timestamp(System.currentTimeMillis())); // Fecha actual
                statement.setTimestamp(15, new Timestamp(System.currentTimeMillis())); // Fecha actual
                statement.executeUpdate();
                System.out.println("Se ha insertado con éxito");
                // Redirigir después de la inserción
                response.sendRedirect("managementobjects.jsp");

            } catch (NumberFormatException e) {
                // Manejar la excepción de formato incorrecto de número
                e.printStackTrace();
                request.setAttribute("error", "Formato de código incorrecto");
                request.getRequestDispatcher("addobject.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al insertar el bien: " + e.getMessage());
                request.getRequestDispatcher("addobject.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // Manejar el caso donde el usuario no existe
            request.setAttribute("error", "El usuario proporcionado no existe");
            request.getRequestDispatcher("addobject.jsp").forward(request, response);
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