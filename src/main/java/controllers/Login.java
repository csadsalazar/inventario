package controllers;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;
import dto.UserResponse;
import models.Object;
import utils.ConnectionBD;
 
@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    
        // Obtén los datos del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String jsonInputString = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
    
        HttpRequest req = HttpRequest.newBuilder()
            .uri(URI.create("http://172.16.10.48:8081/conexionldap/v1/verificarUsuario"))
            .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
            .build();

        HttpResponse<String> resp;
        try {
            resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error en la conexión con el servicio LDAP.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // Procesar la respuesta del servicio LDAP
        Gson gson = new Gson();
        UserResponse userResponse = gson.fromJson(resp.body(), UserResponse.class);
        System.out.println("Response Body: " + resp.body());

        if (resp.statusCode() == 200) {
            // Obtener el ID del usuario desde la base de datos
            int userId;
            try {
                userId = UserController.getUserIdByUsernameLogin(username);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al obtener el ID del usuario desde la base de datos.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            if (userId == -1) {
                // Usuario no encontrado en la base de datos pero la respuesta LDAP es 200
                if (userResponse == null) {
                    request.setAttribute("error", "No se pudo obtener la información del usuario.");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    return;
                }

                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("userResponse", userResponse);
                request.getRequestDispatcher("adduser.jsp").forward(request, response);
                return;
            }

            // Usuario existe en la base de datos
            request.getSession().setAttribute("userId", userId);
            request.getSession().setAttribute("userResponse", userResponse);

            // Verificar el perfil del usuario en la base de datos
            try (Connection conn = ConnectionBD.getConnection()) {
                String queryAdmins = "SELECT * FROM MA_Usuario WHERE usuario = ? AND FK_Perfil = 1";
                PreparedStatement pstmtAdmins = conn.prepareStatement(queryAdmins);
                pstmtAdmins.setString(1, username);
                ResultSet rsAdmins = pstmtAdmins.executeQuery();

                String queryUsers = "SELECT * FROM MA_Usuario WHERE usuario = ? AND FK_Perfil = 2";
                PreparedStatement pstmtUsers = conn.prepareStatement(queryUsers);
                pstmtUsers.setString(1, username);
                ResultSet rsUsers = pstmtUsers.executeQuery();

                if (rsAdmins.next()) {
                    // Usuario es un administrador
                    request.getSession().setAttribute("username", username);
                    request.getRequestDispatcher("homea.jsp").forward(request, response);
                } else if (rsUsers.next()) {
                    // Usuario es un usuario normal
                    int idUsuario = rsUsers.getInt("PK_idUsuario");
                    // Guardar el ID de usuario en la sesión
                    HttpSession session = request.getSession();
                    request.getSession().setAttribute("username", username);
                    session.setAttribute("idUsuario", idUsuario);
                    
                    // Cargar los bienes asociados al usuario
                    String queryBienes = "SELECT * FROM MA_Bien WHERE FK_Usuario = ? AND estado != 'Reportado'";
                    PreparedStatement pstmtBienes = conn.prepareStatement(queryBienes);
                    pstmtBienes.setInt(1, idUsuario);
                    ResultSet rsBienes = pstmtBienes.executeQuery();

                    List<Object> bienesUsuario = new ArrayList<>();
                    while (rsBienes.next()) {
                        Object bien = new Object(
                            rsBienes.getInt("idBien"),
                            rsBienes.getInt("placa"),
                            rsBienes.getLong("PK_Codigo"),
                            rsBienes.getLong("valor"),
                            rsBienes.getTimestamp("fecha"),
                            rsBienes.getTimestamp("fechaAdmin"),
                            rsBienes.getString("nombre"),
                            rsBienes.getString("descripcion"),
                            rsBienes.getString("estado"),
                            rsBienes.getString("condicion"),
                            rsBienes.getString("imagenuno"),
                            rsBienes.getString("imagendos"),
                            rsBienes.getString("imagentres"),
                            rsBienes.getString("observacionAdmin"),
                            null, // user, debes obtenerlo si es necesario
                            null, // admin, debes obtenerlo si es necesario
                            null, // PK_idDependency, debes obtenerlo si es necesario
                            null // information, debes obtenerlo si es necesario
                        );
                        bienesUsuario.add(bien);
                    }
                    session.setAttribute("bienesUsuario", bienesUsuario);
                    request.getRequestDispatcher("homef.jsp").forward(request, response);
                } else {
                    // Usuario no encontrado en la base de datos
                    request.getSession().setAttribute("username", username);
                    request.getRequestDispatcher("adduser.jsp").forward(request, response);
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error en la base de datos.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            // Respuesta del servicio LDAP no es 200
            request.setAttribute("error", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
