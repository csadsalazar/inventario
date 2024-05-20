package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

public class EnviarCorreoServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String recipient = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        // Configurar la clave de API de SendGrid
        String apiKey = "TU_API_KEY"; // Reemplaza con tu clave de API de SendGrid

        // Crear un objeto Email de SendGrid
        Email from = new Email("tu_correo@example.com"); // Remitente
        Email to = new Email(recipient); // Destinatario
        Content content = new Content("text/plain", message); // Contenido del mensaje
        Mail mail = new Mail(from, subject, to, content); // Objeto de correo electrónico

        // Inicializar el cliente de SendGrid
        SendGrid sg = new SendGrid(apiKey);

        // Enviar el correo electrónico
        Request sgRequest = new Request();
        try {
            sgRequest.setMethod(Method.POST);
            sgRequest.setEndpoint("mail/send");
            sgRequest.setBody(mail.build());
            Response sgResponse = sg.api(sgRequest);
            System.out.println(sgResponse.getStatusCode());
            System.out.println(sgResponse.getBody());
            System.out.println(sgResponse.getHeaders());
            response.sendRedirect("prueba.jsp?message=Correo+enviado+exitosamente");
        } catch (IOException e) {
            e.printStackTrace();
            response.sendRedirect("prueba.jsp?message=Error+al+enviar+correo");
        }
    }
}
