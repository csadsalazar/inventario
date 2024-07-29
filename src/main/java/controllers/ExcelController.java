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

        String[] headers = {"Código", "Placa", "Nombre", "Usuario", "Dependencia", "Estado","Valor", "Observacion"};
        for (int i = 0; i < headers.length; i++) {
            Label label = new Label(i, 0, headers[i]);
            sheet.addCell(label);
        }

        int rowNum = 1;
        for (Object bien : listaBienes) {
            sheet.addCell(new jxl.write.Number(0, rowNum, bien.getCode())); 
            sheet.addCell(new jxl.write.Number(1, rowNum, bien.getPlate()));
            sheet.addCell(new Label(2, rowNum, bien.getName()));
            sheet.addCell(new Label(3, rowNum, bien.getUser().getUser()));
            sheet.addCell(new Label(4, rowNum, bien.getPK_idDependency().getDependencyname()));
            sheet.addCell(new Label(5, rowNum, bien.getState()));
            sheet.addCell(new jxl.write.Number(6, rowNum, bien.getValue()));
            sheet.addCell(new Label(7, rowNum, bien.getObservation()));
            rowNum++;
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=reporte.xls");
        workbook.write();
        workbook.close();
    }
}