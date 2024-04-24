/* 

package controllers;

public class correos {

    MailMessage message = new MailMessage();

// Establezca el asunto del mensaje, el cuerpo y la información del remitente
message.setSubject("New message created by Aspose.Email for .NET");
message.setBody("This is the body of the email.");
message.setFrom(new MailAddress("from@domain.com", "Sender Name", false));

// Agregar a destinatarios y destinatarios de CC
message.getTo().addItem(new MailAddress("to1@domain.com", "Recipient 1", false));
message.getCC().addItem(new MailAddress("cc1@domain.com", "Recipient 3", false));

// Agregar archivos adjuntos
message.getAttachments().addItem(new Attachment("word.docx"));

// Guarde el mensaje en formatos EML, EMLX, MSG y MHTML
message.save("EmailMessage.eml", SaveOptions.getDefaultEml());
message.save("EmailMessage.emlx", SaveOptions.createSaveOptions(MailMessageSaveType.getEmlxFormat()));
message.save("EmailMessage.msg", SaveOptions.getDefaultMsgUnicode());
message.save("EmailMessage.mhtml", SaveOptions.getDefaultMhtml());


}

*/