package controllers;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import models.Bien;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/GenerarReportePDFServlet")
public class PDFController {

    public void generarArchivoPDF(List<Bien> listaBienes, HttpServletResponse response) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Lista de Bienes");
                contentStream.endText();

                System.out.println("Generando contenido del PDF...");

                int yPosition = 680;
                contentStream.setFont(PDType1Font.HELVETICA, 10);

                contentStream.beginText();
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText("ID");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Código");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Placa");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Nombre");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Usuario");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Dependencia");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Valor");
                contentStream.endText();

                for (Bien bien : listaBienes) {
                    yPosition -= 20;
                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, yPosition);
                    contentStream.showText(String.valueOf(bien.getidBien()));
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText(String.valueOf(bien.getCodigo()));
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText(String.valueOf(bien.getPlaca()));
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText(bien.getNombre());
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText(bien.getUsuario().getUsuario());
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText(bien.getDependencia().getnombreDependencia());
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText(String.valueOf(bien.getValor()));
                    contentStream.endText();
                }
            }

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=Bienes.pdf");
            document.save(response.getOutputStream());
        }
    }
}
