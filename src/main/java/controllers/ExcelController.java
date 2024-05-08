package controllers;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import models.Object;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelController {
    public void generateFileExcel(List<Object> listaBienes, HttpServletResponse response) throws IOException, RowsExceededException, WriteException {
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        WritableSheet sheet = workbook.createSheet("Bienes", 0);

        String[] headers = {"Código", "Placa", "Nombre", "Usuario", "Dependencia", "Estado","Valor"};
        for (int i = 0; i < headers.length; i++) {
            Label label = new Label(i, 0, headers[i]);
            sheet.addCell(label);
        }

        int rowNum = 1;
        for (Object bien : listaBienes) {
            sheet.addCell(new jxl.write.Number(0, rowNum, bien.getCodigo()));
            sheet.addCell(new jxl.write.Number(1, rowNum, bien.getPlaca()));
            sheet.addCell(new Label(2, rowNum, bien.getNombre()));
            sheet.addCell(new Label(3, rowNum, bien.getUsuario().getUsuario()));
            sheet.addCell(new Label(4, rowNum, bien.getDependencia().getnombreDependencia()));
            sheet.addCell(new Label(5, rowNum, bien.getEstado()));
            sheet.addCell(new jxl.write.Number(6, rowNum, bien.getValor()));
            rowNum++;
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=reporte.xls");
        workbook.write();
        workbook.close();
    }
}