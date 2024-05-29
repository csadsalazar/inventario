package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import models.Object;

@WebServlet("/GenerateExcelReport")
public class GenerateExcelReport extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        @SuppressWarnings("unchecked")
        List<Object> bienesUsuario = (List<Object>) request.getSession().getAttribute("bienesUsuario");

        ExcelController excelController = new ExcelController();
        try {
            excelController.generateFileExcel(bienesUsuario, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar cualquier error
            response.sendRedirect("homef.jsp");
        }
    }
}
