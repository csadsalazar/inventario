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

        String funcionario = request.getParameter("Funcionario");
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        int placa = Integer.parseInt(request.getParameter("placa"));
        String nombre = request.getParameter("nombre");
        String ubicacion = request.getParameter("descripcion");
        String descripcion = request.getParameter("grupo");
        int valor = Integer.parseInt(request.getParameter("imagenes"));


        try{
        Connection conn = ConexionBD.getConnection();
        String sql = "INSERT INTO MA_Bienes (PK_Codigo, nombre, placa, descripcion, valor, FK_Usuario, ubicacion, imagen) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, codigo);
            statement.setString(2, nombre);
            statement.setInt(3, placa);
            statement.setString(4, descripcion);
            statement.setInt(5, valor);
            statement.setString(6, funcionario);
            statement.setString(7, ubicacion);
            statement.setString(8, null); // assuming no image is being uploaded

            statement.executeUpdate();

            System.out.println("Se ha insertado con exito");    
            request.getRequestDispatcher("gestionbienes.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al agregar el bien: " + e.getMessage());
            request.getRequestDispatcher("agregarbien.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
