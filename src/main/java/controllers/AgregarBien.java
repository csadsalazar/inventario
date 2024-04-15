package controllers;

import utils.ConexionBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Dependencia;


@WebServlet("/AgregarBien")
public class AgregarBien extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener y enviar la lista de dependencias al JSP
        List<Dependencia> dependencias = DependenciaController.obtenerDependencias();
        System.out.println("Cantidad de dependencias: " + dependencias.size()); // Añadir esta línea
        request.setAttribute("dependencias", dependencias);
        request.getRequestDispatcher("agregarbien.jsp").forward(request, response);
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Tu lógica existente para agregar bienes
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        int placa = Integer.parseInt(request.getParameter("placa"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        int valor = Integer.parseInt(request.getParameter("valor"));
        String usuario = request.getParameter("usuario");
        int dependencia = Integer.parseInt(request.getParameter("dependencia"));
        String estado = request.getParameter("estado");

        // Verificar si el usuario existe antes de agregar el bien
        if (UsuarioController.usuarioExiste(usuario)) {
            try {
                // Obtener el ID del usuario
                int idUsuario = UsuarioController.obtenerIdUsuario(usuario);

                // Establecer la conexión y realizar la inserción en la base de datos
                Connection conn = ConexionBD.getConnection();
                String sql = "INSERT INTO MA_Bienes (PK_Codigo, placa, nombre, descripcion, valor, FK_Usuario, FK_Ubicacion, estado) VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, codigo);
                statement.setInt(2, placa);
                statement.setString(3, nombre);
                statement.setString(4, descripcion);
                statement.setInt(5, valor);
                statement.setInt(6, idUsuario);
                statement.setInt(7, dependencia);
                statement.setString(8, estado);
                statement.executeUpdate();
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
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
        } else {
            // Manejar el caso donde el usuario no existe
            request.setAttribute("error", "El usuario proporcionado no existe");
            request.getRequestDispatcher("agregarbien.jsp").forward(request, response);
        }
    }
}
