package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/EliminarBien")
public class EliminarBien extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        boolean eliminado = false;
        try {
            eliminado = eliminarBien(codigo);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
    }

    private boolean eliminarBien(String codigo) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConexionBD.getConnection(); // Utiliza tu método para obtener la conexión
            String sql = "DELETE FROM MA_Bienes WHERE PK_Codigo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, codigo);
            int filasAfectadas = stmt.executeUpdate();
            System.out.println("Se ha eliminado el bien con código: " + codigo);
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Cierra la conexión y el statement
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
