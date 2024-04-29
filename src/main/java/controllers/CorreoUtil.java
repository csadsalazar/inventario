package controllers;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class CorreoUtil {

    public static void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        // Configuración del servidor SMTP
        String host = "smtp.gmail.com";
        String usuario = "cdt200604@gmail.com";
        String contraseña = "cesardavid200688";

        // Propiedades de la sesión
        Properties propiedades = System.getProperties();
        propiedades.setProperty("mail.smtp.host", host);
        propiedades.setProperty("mail.smtp.auth", "true");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");

        // Crear una nueva sesión con autenticación
        Session session = Session.getInstance(propiedades, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, contraseña);
            }
        });

        try {
            // Crear un objeto MimeMessage
            MimeMessage mensaje = new MimeMessage(session);

            // Configurar el remitente y destinatario
            mensaje.setFrom(new InternetAddress(usuario));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

            // Establecer el asunto y cuerpo del mensaje
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);

            // Enviar el mensaje
            Transport.send(mensaje);
            System.out.println("Correo enviado exitosamente.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            System.out.println("Error al enviar el correo.");
        }
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        enviarCorreo("cdt200604@gmail.com", "Asunto del correo", "Contenido del correo");
    }
}
