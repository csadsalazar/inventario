package controllers;
import models.Dependency;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetDependenciesServlet")
public class GetDependenciesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8"); // Asegúrate de que se usa UTF-8 para la codificación
        PrintWriter out = response.getWriter();

        try {
            ArrayList<Dependency> dependencias = ListDependencies.getDependencies();
            Gson gson = new Gson();
            String jsonDependencias = gson.toJson(dependencias);
            out.println(jsonDependencias);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
