package controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Object;
import models.User;
import utils.ConnectionBD;

@WebServlet("/Login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("inputUserName");
        String contrasena = request.getParameter("inputUserPassword");

        Connection conn = null;
        try {
            conn = ConnectionBD.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp");
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
                // Obtener el ID de usuario
                int idUsuario = rsUsuarios.getInt("PK_idUsuario");
                
                // Guardar el ID de usuario en la sesión
                HttpSession session = request.getSession();
                session.setAttribute("idUsuario", idUsuario);
                
                // Mensaje de depuración para verificar si la sesión se está configurando correctamente
                System.out.println("Sesión establecida para el usuario: " + usuario);

                // Cargar los bienes asociados al usuario y guardarlos en la sesión
                String queryBienes = "SELECT * FROM MA_Bienes WHERE FK_Usuario = ?";
                PreparedStatement pstmtBienes = conn.prepareStatement(queryBienes);
                pstmtBienes.setInt(1, idUsuario);
                ResultSet rsBienes = pstmtBienes.executeQuery();

                // Crear una lista para almacenar los bienes del usuario
                List<Object> bienesUsuario = new ArrayList<>();
                while (rsBienes.next()) {
                    // Crear objetos Bien y agregarlos a la lista
                    Object bien = new Object(
                            rsBienes.getInt("idBien"),
                            rsBienes.getLong("PK_Codigo"),
                            rsBienes.getString("nombre"),
                            rsBienes.getInt("placa"),
                            rsBienes.getString("descripcion"),
                            rsBienes.getLong("valor"),
                            null, // Debes obtener el usuario asociado a este bien si es necesario
                            null, // Debes obtener la dependencia asociada a este bien si es necesario
                            rsBienes.getString("estado"),
                            rsBienes.getString("imagen1"),
                            rsBienes.getString("imagen2"),
                            null // Debes obtener la observación asociada a este bien si es necesario
                    );
                    bienesUsuario.add(bien);
                }

                // Guardar la lista de bienes del usuario en la sesión
                session.setAttribute("bienesUsuario", bienesUsuario);
                
                // Mensaje de depuración para verificar si los bienes del usuario se están guardando en la sesión correctamente
                System.out.println("Bienes del usuario guardados en la sesión.");

                // Verificar si el usuario es un administrador activo
                String queryAdmins = "SELECT * FROM MA_Administradores WHERE usuario = ? AND estado = 'Activo'";
                PreparedStatement pstmtAdmins = conn.prepareStatement(queryAdmins);
                pstmtAdmins.setString(1, usuario);
                ResultSet rsAdmins = pstmtAdmins.executeQuery();

                // Si el usuario también está en la tabla MA_Administradores y está activo, redirigir a homea.jsp
                if (rsAdmins.next()) {
                    User user = new User(idUsuario, rsUsuarios.getString("nombre"), usuario, rsUsuarios.getInt("cedula"), contrasena, rsUsuarios.getString("dependencia"), rsUsuarios.getString("cargo"), rsUsuarios.getString("contrato"), rsUsuarios.getString("sede"));
                    session.setAttribute("usuario", user);
                    response.sendRedirect("homea.jsp");
                } else {
                    // Si el usuario no es un administrador activo, redirigir a homef.jsp
                    response.sendRedirect("homef.jsp");
                }
            } else {
                // Si el usuario no existe en MA_Usuarios, mostrar mensaje de error
                response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp");
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