package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/ReportObject")
public class ReportObject extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        boolean reportado = false;
        try {
            reportado = reportObject(codigo);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean reportObject(String codigo) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionBD.getConnection(); // Utiliza tu método para obtener la conexión
            String sql = "UPDATE MA_Bien SET estado='En espera' WHERE PK_Codigo=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, codigo);
            int filasAfectadas = stmt.executeUpdate();
            System.out.println("Se ha puesto en espera el bien con código: " + codigo);
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