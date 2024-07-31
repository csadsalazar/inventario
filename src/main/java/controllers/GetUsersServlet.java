package controllers;

import models.User;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetUsersServlet")
public class GetUsersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            // Obtener la lista de todos los usuarios
            ArrayList<User> usuarios = ListUsers.getUsers();

            // Convertir la lista de usuarios a formato JSON y enviarla como respuesta
            Gson gson = new Gson();
            String jsonUsuarios = gson.toJson(usuarios);
            out.println(jsonUsuarios);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Manejar el error
        }
    }
}

