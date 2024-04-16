package controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import models.Usuario;
import utils.ConexionBD;

@WebServlet("/Login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("inputUserName");
        String contrasena = request.getParameter("inputUserPassword");
    
        Connection conn = null;
        try {
            conn = ConexionBD.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?error=3");
            return; 
        }
    
        try {
            // Consulta para verificar si el usuario existe en MA_Usuarios
            String queryUsuarios = "SELECT * FROM MA_Usuarios WHERE usuario = ? AND contrasena = ?";
            PreparedStatement pstmtUsuarios = conn.prepareStatement(queryUsuarios);
            pstmtUsuarios.setString(1, usuario);
            pstmtUsuarios.setString(2, contrasena);
            ResultSet rsUsuarios = pstmtUsuarios.executeQuery();
    
            // Verificar si el usuario existe en MA_Usuarios
            if (rsUsuarios.next()) {
                // Verificar si el usuario es un administrador activo
                String queryAdmins = "SELECT * FROM MA_Administradores WHERE usuario = ? AND estado = 'Activo'";
                PreparedStatement pstmtAdmins = conn.prepareStatement(queryAdmins);
                pstmtAdmins.setString(1, usuario);
                ResultSet rsAdmins = pstmtAdmins.executeQuery();
    
                // Si el usuario también está en la tabla MA_Administradores y está activo, redirigir a homea.jsp
                if (rsAdmins.next()) {
                    Usuario user = new Usuario(rsUsuarios.getInt("PK_idUsuario"), rsUsuarios.getString("nombre"), rsUsuarios.getString("usuario"), rsUsuarios.getInt("cedula"), rsUsuarios.getString("contrasena"), rsUsuarios.getString("dependencia"), rsUsuarios.getString("cargo"), rsUsuarios.getString("contrato"), rsUsuarios.getString("sede"));
                    request.getSession().setAttribute("usuario", user);
                    response.sendRedirect("homea.jsp");
                } else {
                    // Si el usuario no es un administrador activo, redirigir a homef.jsp
                    response.sendRedirect("homef.jsp");
                }
            } else {
                // Si el usuario no existe en MA_Usuarios, mostrar mensaje de error
                response.sendRedirect("index.jsp?error=1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?error=4");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
} 
