package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import models.Object;

@WebServlet("/CheckBienesStatus")
public class CheckBienesStatus extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        @SuppressWarnings("unchecked")
        List<Object> bienesUsuario = (List<Object>) request.getSession().getAttribute("bienesUsuario");
        boolean todosListos = true;

        for (Object bien : bienesUsuario) {
            if (!bien.getState().equals("En espera") && !bien.getState().equals("Reportado")) {
                todosListos = false;
                break;
            }
        }

        if (todosListos) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("error");
        }
    }
}
