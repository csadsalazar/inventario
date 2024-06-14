package controllers;

import utils.ConnectionBD;
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
import models.Dependency;
import models.Charge;
import models.DocumentType;

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null; 
        try {
            conn = ConnectionBD.getConnection();
            List<Dependency> dependencias = ListDependencies.getDependencies();
            request.setAttribute("dependencia", dependencias);
            request.getRequestDispatcher("adduser.jsp").forward(request, response);

            conn = ConnectionBD.getConnection();
            List<Charge> charges = ListCharge.getCharges();
            request.setAttribute("cargo", charges);
            request.getRequestDispatcher("adduser.jsp").forward(request, response);

            conn = ConnectionBD.getConnection();
            List<DocumentType> documents = ListDocumentType.getDocumentType();
            request.setAttribute("tipodocumento", documents);
            request.getRequestDispatcher("adduser.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Manejar el error, por ejemplo, redirigiendo a una página de error
            request.setAttribute("error", "Error al obtener las dependencias: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } finally {
            // Cerrar la conexión en el bloque finally para asegurarse de que se cierre correctamente
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    } 
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String nombre = request.getParameter("nombre").toUpperCase();
    String username = request.getParameter("username");
    Long cedula = Long.parseLong(request.getParameter("cedula"));
    int dependenciaId = Integer.parseInt(request.getParameter("dependencia")); // Obtener el ID de la dependencia
    int cargoId = Integer.parseInt(request.getParameter("cargo")); // Obtener el ID del cargo
    int tipodocumentoId = Integer.parseInt(request.getParameter("tipodocumento")); // Obtener el ID del tipo de documento


    try {
        // Establecer la conexión y realizar la inserción en la base de datos
        Connection conn = ConnectionBD.getConnection();
        String sql = "INSERT INTO MA_Usuario (nombre, cedula, usuario, FK_Dependencia, FK_Cargo, FK_TipoDocumento) VALUES (?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, nombre.toUpperCase()); // Convertir el nombre a mayúsculas
        statement.setLong(2, cedula);
        statement.setString(3, username);  
        statement.setInt(4, dependenciaId);      
        statement.setInt(5, cargoId);  
        statement.setInt(6, tipodocumentoId);
        statement.executeUpdate();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        System.out.println("Se ha insertado con éxito el usuario"); 
        request.getRequestDispatcher("homef.jsp").forward(request, response);
    
    } catch (NumberFormatException e) {
        // Manejar la excepción de formato incorrecto de número
        e.printStackTrace();
        request.setAttribute("error", "Formato del numero incorrecto");
        request.getRequestDispatcher("adduser.jsp").forward(request, response);
    
    } catch (SQLException e) {
        e.printStackTrace();
        request.setAttribute("error", "Error al agregar el usuario");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();    
    }
    }
}