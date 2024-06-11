package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.ConnectionBD;
 
@WebServlet("/AddAdmin")
public class AddAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        // Obtén los datos del formulario
        String username = request.getParameter("username");
        String estado = request.getParameter("estado");
 
        // Crea la conexión HTTP
        URL url = new URL("http://localhost:9560/conexionldap/v1/verificarUsuario");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
 
        // Crea el JSON con los datos del formulario
        String jsonInputString = "{\"username\": \"" + username + "\"}";
 
        // Enviar la solicitud POST
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);          
        }
 
        // Leer la respuesta del servicio
        int responseCode;
        StringBuilder responseStrBuilder = new StringBuilder();
 
        try {
            responseCode = con.getResponseCode();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    responseStrBuilder.append(responseLine.trim());
                }
            }
        } catch (IOException e) {
            // Captura la excepción y maneja el error 
            responseCode = con.getResponseCode();
            request.setAttribute("error", "Error del servidor");
            request.getRequestDispatcher("addadmin.jsp").forward(request, response);
            return;
        }
 
        // Procesar la respuesta según sea necesario
        try (Connection conn = ConnectionBD.getConnection()) {
            if (responseCode == 200) { 
                // Autenticación exitosa
                String sql = "INSERT INTO MA_Administradores (usuario, estado) VALUES (?,?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, estado);
                statement.executeUpdate();
                conn.close(); // Cerrar la conexión JDBC
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                System.out.println("Se ha insertado con éxito");
                request.getRequestDispatcher("managementadmins.jsp").forward(request, response);

            } else {
                // Autenticación fallida
                request.setAttribute("error", "Usuario incorrecto o no existente en ldpa");
                request.getRequestDispatcher("managementadmins.jsp").forward(request, response); // Redirige de vuelta al login con mensaje de error
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
