package controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        // Obtén los datos del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");
 
        // Crea la conexión HTTP
        URL url = new URL("http://localhost:8990/conexionldap/v1/verificarUsuario");
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
 
        // Procesar la respuesta según sea necesario
        String responseBody = responseStrBuilder.toString();
 
        if (responseCode == 200) { // Ajusta según la respuesta real
            // Autenticación exitosa
            request.getSession().setAttribute("username", username);
            request.getRequestDispatcher("homea.jsp").forward(request, response); // Redirige a la página de bienvenida
        } else {
            // Autenticación fallida
            request.setAttribute("errorMessage", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("index.jsp").forward(request, response); // Redirige de vuelta al login con mensaje de error
        }
    }
}
 