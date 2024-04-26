package controllers;

import models.Dependencia;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ObtenerDependenciasServlet")
public class ObtenerDependenciasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            ArrayList<Dependencia> dependencias = ListarDependencias.obtenerDependencias();
            Gson gson = new Gson();
            String jsonDependencias = gson.toJson(dependencias);
            out.println(jsonDependencias);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Manejar el error
        }
    }
}
