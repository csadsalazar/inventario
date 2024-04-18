package controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import models.Usuario;
import utils.ConexionBD;

@WebServlet("/homef")
public class VerBienesUsuario extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario != null) {
            Connection conn = null;
            try {
                conn = ConexionBD.getConnection();
                String sql = "SELECT usuario, dependencia, cargo FROM MA_Usuarios WHERE PK_idUsuario = ?";
                PreparedStatement psu = conn.prepareStatement(sql);
                psu.setInt(1, usuario.getPK_idUsuario());
                ResultSet  rsu = psu.executeQuery();
                // Consulta para obtener los bienes del usuario
                String query = "SELECT * FROM MA_Bienes WHERE FK_Usuario = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, usuario.getPK_idUsuario());
                ResultSet rs = ps.executeQuery();

                // Colocar los resultados en un atributo de solicitud y reenviar a la página de visualización de bienes
                request.setAttribute("bienesUsuario", rsu);
                request.setAttribute("bienesUsuario", rs);
                request.getRequestDispatcher("homef.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
