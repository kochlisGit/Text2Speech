package document.writer;

import document.entity.Document;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

public class ExcelWriter implements DocumentWriter {

    // Creates an EXCEL document.
    @Override
    public boolean writeDocument(final Document document) {
        final XSSFWorkbook excelDoc = new XSSFWorkbook();
        final XSSFSheet sheet = excelDoc.createSheet("Text2Speech");
        final String[] documentRows = document.getText().split("\n");
        int rowCount = 0;

        for (String rowStr : documentRows) {
            final Row row = sheet.createRow(rowCount++);
            final String[] documentCols = rowStr.split(",");
            int columnCount = 0;

            for (String colStr : documentCols) {
                Cell cell = row.createCell(columnCount++);
                cell.setCellValue(colStr);
            }
        }

        try {
            excelDoc.write(new FileOutputStream(document.getFilePath()));
            excelDoc.close();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
