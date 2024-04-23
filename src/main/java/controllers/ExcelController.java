package controllers;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import models.Bien;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/GenerarReporteExcelServlet")
public class ExcelController {

    public void generarArchivoExcel(List<Bien> listaBienes, HttpServletResponse response) throws IOException, RowsExceededException, WriteException {
        
        System.out.println("Generando archivo Excel...");
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        WritableSheet sheet = workbook.createSheet("Bienes", 0);

        String[] headers = {"ID", "Código", "Placa", "Nombre", "Usuario", "Dependencia", "Valor"};
        for (int i = 0; i < headers.length; i++) {
            Label label = new Label(i, 0, headers[i]);
            sheet.addCell(label);
        }

        int rowNum = 1;
        for (Bien bien : listaBienes) {
            sheet.addCell(new jxl.write.Number(0, rowNum, bien.getidBien()));
            sheet.addCell(new jxl.write.Number(1, rowNum, bien.getCodigo()));
            sheet.addCell(new jxl.write.Number(2, rowNum, bien.getPlaca()));
            sheet.addCell(new Label(3, rowNum, bien.getNombre()));
            sheet.addCell(new Label(4, rowNum, bien.getUsuario().getUsuario()));
            sheet.addCell(new Label(5, rowNum, bien.getDependencia().getnombreDependencia()));
            sheet.addCell(new jxl.write.Number(6, rowNum, bien.getValor()));
            rowNum++;
        }

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=Bienes.xls");
        workbook.write();
        workbook.close();
    }
}
