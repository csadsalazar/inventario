package controllers;

import utils.ConexionBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AgregarBien")
public class AgregarBien extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        int placa = Integer.parseInt(request.getParameter("placa"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        int valor = Integer.parseInt(request.getParameter("valor"));
        String funcionario = request.getParameter("funcionario");
        String ubicacion = request.getParameter("ubicacion");

        
        try {
            // Establecer la conexión y realizar la inserción en la base de datos
            Connection conn = ConexionBD.getConnection();
            String sql = "INSERT INTO MA_Bienes (PK_Codigo, placa, nombre, descripcion, valor, FK_Usuario, ubicacion, estado) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, codigo);
            statement.setInt(2, placa);
            statement.setString(3, nombre);
            statement.setString(4, descripcion);
            statement.setInt(5, valor);
            statement.setString(6, funcionario);
            statement.setString(7, ubicacion);
            statement.setString(8, "No reportado");

            statement.executeUpdate();

            System.out.println("Se ha insertado con éxito");
            request.getRequestDispatcher("gestionbienes.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Manejar la excepción de formato incorrecto de número
            e.printStackTrace();
            request.getRequestDispatcher("agregarbien.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al agregar el bien: " + e.getMessage());
            request.getRequestDispatcher("agregarbien.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
