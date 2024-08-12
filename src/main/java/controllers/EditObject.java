package controllers;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import models.Object;
import utils.ConnectionBD;

@WebServlet("/EditObject")
@MultipartConfig
public class EditObject extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String STORAGE_BASE_URL = "https://storagepermlabesinvima.blob.core.windows.net/";
    private static final String CONTAINER_NAME = "inventariopersonalizado";
    private static final String SAS_TOKEN = "sv=2022-11-02&ss=bfqt&srt=sco&sp=rwlacupitfx&se=2029-08-01T03:19:53Z&st=2024-07-31T19:19:53Z&spr=https&sig=SXXVMTTVpVE2jfuWXemUCeU8kKnoaBpZ%2B02C4iIWBI8%3D";
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        try {
            // Obtener el código del bien a actualizar
            long codigo = Long.parseLong(request.getParameter("codigo"));

            // Establecer la conexión y realizar la consulta para obtener los datos del bien
            conn = ConnectionBD.getConnection();
            String sql = "SELECT * FROM ADMINISTRATIVA.AL_INV.MA_Bien WHERE PK_Codigo=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, codigo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Obtener los datos del bien de la consulta
                long codigoBien = resultSet.getLong("PK_Codigo");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                long valor = resultSet.getLong("valor");
                int idDependencia = resultSet.getInt("FK_Dependencia");
                String observacion = resultSet.getString("observacionAdmin");
                String imagenuno = resultSet.getString("imagenuno");
                String imagendos = resultSet.getString("imagendos");
                String imagentres = resultSet.getString("imagentres");

                // Crear un objeto bien y establecer sus propiedades
                Object bien = new Object();
                bien.setCode(codigoBien);
                bien.setName(nombre);
                bien.setDescription(descripcion);
                bien.setValue(valor);
                bien.setObservation(observacion);
                bien.setImageOne(imagenuno); 
                bien.setImageTwo(imagendos);
                bien.setImageThree(imagentres);

                System.out.println("Imagen Uno: " + imagenuno);
                System.out.println("Imagen Dos: " + imagendos);
                System.out.println("Imagen Tres: " + imagentres);

                // Obtener la dependencia asociada al bien
                Dependency dependencia = ListDependencies.getDependencyById(idDependencia);
                request.setAttribute("dependenciaBien", dependencia);

                // Pasar el objeto bien al JSP como un atributo de solicitud
                request.setAttribute("bien", bien);

                // Obtener la lista completa de dependencias para el formulario addobject.jsp
                List<Dependency> dependencias = ListDependencies.getDependencies();
                request.setAttribute("dependencias", dependencias);

                // Redirigir al formulario de edición
                request.getRequestDispatcher("editobject.jsp").forward(request, response);
            } else {
                // Manejar el caso donde no se encuentra el bien
                request.setAttribute("error", "El bien con el código " + codigo + " no existe");
                request.getRequestDispatcher("managementobjects.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            // Manejar la excepción de formato incorrecto de número
            e.printStackTrace();
            request.setAttribute("error", "Formato de código incorrecto");
            request.getRequestDispatcher("managementobjects.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Manejar el error, por ejemplo, redirigiendo a una página de error
            request.setAttribute("error", "Error al obtener los datos del bien: " + e.getMessage());
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
        String estado = request.getParameter("estado");
        String observacion = request.getParameter("observacion");

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

        // Verificar si el usuario existe antes de actualizar el bien
        if (UserController.userExists(usuario)) {
            try {
                // Obtener el ID del usuario
                int idUsuario = UserController.getUserId(usuario);

                // Establecer la conexión y realizar la actualización en la base de datos
                Connection conn = ConnectionBD.getConnection();
                String sql = "UPDATE ADMINISTRATIVA.AL_INV.MA_Bien SET FK_Usuario=?, nombre=?, descripcion=?, valor=?, FK_Dependencia=?, estado=?, observacionAdmin=?, FK_UsuarioAdmin=?, imagenuno=?, imagendos=?, imagentres=?, fechaAdmin=? WHERE PK_Codigo=?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, idUsuario);
                statement.setString(2, nombre);
                statement.setString(3, descripcion);
                statement.setLong(4, valor);
                statement.setInt(5, dependenciaId);
                statement.setString(6, estado);
                statement.setString(7, observacion);
                statement.setInt(8, idUsuarioAdmin);
                statement.setString(9, imagenuno);
                statement.setString(10, imagendos);
                statement.setString(11, imagentres);
                statement.setTimestamp(12, new Timestamp(System.currentTimeMillis())); // Fecha actual
                statement.setLong(13, codigo);
                statement.executeUpdate();
                System.out.println("Se ha actualizado con éxito");
                // Redirigir después de la actualización
                response.sendRedirect("managementobjects.jsp");

            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("error", "Formato de código incorrecto");
                request.getRequestDispatcher("editobject.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al actualizar el bien: " + e.getMessage());
                request.getRequestDispatcher("editobject.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("error", "El usuario proporcionado no existe");
            request.getRequestDispatcher("editobject.jsp").forward(request, response);
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
        return null; // Si no hay archivo, retornar null
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