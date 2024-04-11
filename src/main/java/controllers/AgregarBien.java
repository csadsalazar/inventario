package controllers;

import utils.ConexionBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String usuario = request.getParameter("usuario");
        String ubicacion = request.getParameter("ubicacion");
        String estado = request.getParameter("estado");


        // Verificar si el usuario existe antes de agregar el bien
        if (usuarioExiste(usuario)) {
            try {
                // Obtener el ID del usuario
                int idUsuario = obtenerIdUsuario(usuario);

                // Establecer la conexión y realizar la inserción en la base de datos
                Connection conn = ConexionBD.getConnection();
                String sql = "INSERT INTO MA_Bienes (PK_Codigo, placa, nombre, descripcion, valor, FK_Usuario, ubicacion, estado) VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, codigo);
                statement.setInt(2, placa);
                statement.setString(3, nombre);
                statement.setString(4, descripcion);
                statement.setInt(5, valor);
                statement.setInt(6, idUsuario);
                statement.setString(7, ubicacion);
                statement.setString(8, estado);
                statement.executeUpdate();
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("success");

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

    // Método para verificar si un usuario existe en la base de datos
    private boolean usuarioExiste(String nombreUsuario) {
        Connection conn = null;
        try {
            conn = ConexionBD.getConnection();
            String sql = "SELECT * FROM MA_Usuarios WHERE usuario=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Si hay resultados, el usuario existe
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
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

    // Método para obtener el ID del usuario a partir de su nombre de usuario
    private int obtenerIdUsuario(String nombreUsuario) throws SQLException, ClassNotFoundException {
        Connection conn = ConexionBD.getConnection();
        String sql = "SELECT PK_idUsuario FROM MA_Usuarios WHERE usuario=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombreUsuario);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("PK_idUsuario");
        } else {
            return -1; // Si no se encuentra el usuario, devolver un valor negativo
        }
    }
}
