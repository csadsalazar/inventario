package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Object;
import utils.ConnectionBD;

@WebServlet("/ListBienes")
public class ListBienesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        List<Object> bienesUsuario = new ArrayList<>();
        try (Connection conn = ConnectionBD.getConnection()) {
            // Modificar la consulta para filtrar los bienes
            String queryBienes = "SELECT * FROM MA_Bien WHERE FK_Usuario = ? AND estado != 'Reportado' AND condicion = 'Activo'";
            PreparedStatement pstmtBienes = conn.prepareStatement(queryBienes);
            pstmtBienes.setInt(1, userId);
            ResultSet rsBienes = pstmtBienes.executeQuery();

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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error en la base de datos.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        session.setAttribute("bienesUsuario", bienesUsuario);
        request.getRequestDispatcher("homef.jsp").forward(request, response);
    }
}