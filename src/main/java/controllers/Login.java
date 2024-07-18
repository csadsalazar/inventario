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
import java.util.ArrayList;
import java.util.List;
import models.Object;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ConnectionBD;
 
@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        // Obtén los datos del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");

 
        // Crea la conexión HTTP
        URL url = new URL("http://localhost:9767/conexionldap/v1/verificarUsuario");
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
            request.setAttribute("errorMessage", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
 
            try (Connection conn = ConnectionBD.getConnection()) {
            // Verificar si el usuario es un administrador activo
            String queryAdmins = "SELECT * FROM MA_Usuario WHERE usuario = ? AND FK_Perfil = 1";
            PreparedStatement pstmtAdmins = conn.prepareStatement(queryAdmins);
            pstmtAdmins.setString(1, username);
            ResultSet rsAdmins = pstmtAdmins.executeQuery();

            // Verificar si el usuario se encuentra registrado activo
            String queryUsers = "SELECT * FROM MA_Usuario WHERE usuario = ? AND FK_Perfil = 2"; 
            PreparedStatement pstmtUsers = conn.prepareStatement(queryUsers);
            pstmtUsers.setString(1, username);
            ResultSet rsUsers = pstmtUsers.executeQuery();
 
            if (responseCode == 200 && rsAdmins.next()) { 
                request.getSession().setAttribute("username", username);
                request.getRequestDispatcher("homea.jsp").forward(request, response); 
            } else if (responseCode == 200 && rsUsers.next()) {
                request.getSession().setAttribute("username", username);
                // Obtener el ID de usuario
                int idUsuario = rsUsers.getInt("PK_idUsuario");
                    
                // Guardar el ID de usuario en la sesión
                HttpSession session = request.getSession();
                session.setAttribute("idUsuario", idUsuario);
                   
                // Mensaje de depuración para verificar si la sesión se está configurando correctamente
                System.out.println("Sesión establecida para el usuario: " + username);

                // Cargar los bienes asociados al usuario y guardarlos en la sesión
                String queryBienes = "SELECT * FROM MA_Bien WHERE FK_Usuario = ? AND estado != 'Reportado'";
                PreparedStatement pstmtBienes = conn.prepareStatement(queryBienes);
                pstmtBienes.setInt(1, idUsuario);
                ResultSet rsBienes = pstmtBienes.executeQuery();

                // Crear una lista para almacenar los bienes no reportados del usuario
                List<Object> bienesUsuario = new ArrayList<>();
                while (rsBienes.next()) {
                // Crear objetos Bien y agregarlos a la lista
                Object bien = new Object(
                rsBienes.getInt("idBien"),
                rsBienes.getInt("placa"), // Suponiendo que placa es un entero
                rsBienes.getLong("PK_Codigo"),
                rsBienes.getLong("valor"),
                rsBienes.getDate("fecha"),
                rsBienes.getDate("fechaAdmin"),
                rsBienes.getString("nombre"),
                rsBienes.getString("descripcion"),
                rsBienes.getString("estado"),
                rsBienes.getString("condicion"),
                rsBienes.getString("imagen1"),
                rsBienes.getString("imagen2"),
                rsBienes.getString("imagen3"),
                rsBienes.getString("observacionAdmin"),
                null, // user, debes obtenerlo si es necesario
                null, // admin, debes obtenerlo si es necesario
                null, // PK_idDependency, debes obtenerlo si es necesario
                null // information, debes obtenerlo si es necesario
                );
                bienesUsuario.add(bien);
                }
                // Guardar la lista de bienes del usuario en la sesión
                session.setAttribute("bienesUsuario", bienesUsuario);
                // Mensaje de depuración para verificar si los bienes del usuario se están guardando en la sesión correctamente
                System.out.println("Bienes del usuario guardados en la sesión.");
                request.getRequestDispatcher("homef.jsp").forward(request, response); 

            } else if (responseCode == 200) {
                request.getSession().setAttribute("username", username);
                request.getRequestDispatcher("adduser.jsp").forward(request, response); 
            } else {
                request.setAttribute("error", "Usuario o contraseña incorrectos.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}