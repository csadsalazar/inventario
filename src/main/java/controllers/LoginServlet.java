package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.ConnectionBD;
 
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        // Obtén los datos del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");
 
        // Crea la conexión HTTP
        URL url = new URL("http://localhost:9560/conexionldap/v1/verificarUsuario");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
 
        // Crea el JSON con los datos del formulario
        String jsonInputString = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
 
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
            request.setAttribute("errorMessage", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
 
            try (Connection conn = ConnectionBD.getConnection()) {
            // Verificar si el usuario es un administrador activo
            String queryAdmins = "SELECT * FROM MA_Administrador WHERE usuario = ? AND estado = 'Activo'";
            PreparedStatement pstmtAdmins = conn.prepareStatement(queryAdmins);
            pstmtAdmins.setString(1, username);
            ResultSet rsAdmins = pstmtAdmins.executeQuery();

            // Verificar si el usuario se encuentra registrado activo
            String queryUsers = "SELECT * FROM MA_Usuario WHERE usuario = ?"; 
            PreparedStatement pstmtUsers = conn.prepareStatement(queryUsers);
            pstmtUsers.setString(1, username);
            ResultSet rsUsers = pstmtUsers.executeQuery();
 
            if (responseCode == 200) { 
                // Autenticación exitosa
                request.getSession().setAttribute("username", username);
                request.getRequestDispatcher("register.jsp").forward(request, response); 
            } else if (responseCode == 200 && rsAdmins.next()) {
                request.getSession().setAttribute("username", username);
                request.getRequestDispatcher("homea.jsp").forward(request, response); 
            } else if (responseCode == 200 && rsUsers.next()) {
                request.getSession().setAttribute("username", username);
                request.getRequestDispatcher("homef.jsp").forward(request, response); 
            }else {
                // Autenticación fallida
                request.setAttribute("error", "Usuario o contraseña incorrectos.");
                request.getRequestDispatcher("index.jsp").forward(request, response); // Redirige de vuelta al login con mensaje de error
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}