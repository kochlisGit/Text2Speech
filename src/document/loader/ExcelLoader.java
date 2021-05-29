package document.loader;

import document.builder.DocumentBuilder;
import document.entity.Document;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.Iterator;

public class ExcelLoader implements DocumentLoader {
    private static final double DEFAULT_FONT_SIZE = 12;
    private static final String DEFAULT_FONT_FAMILY = "Arial";

    // Loads all the sheets of an EXCEL file.
    @Override
    public Document loadDocument(final String filePath) {
        try {
            final XSSFWorkbook excelDoc = new XSSFWorkbook(new FileInputStream(filePath));
            final int nSheets = excelDoc.getNumberOfSheets();
            final StringBuilder textBuilder = new StringBuilder();

            for (int i = 0; i < nSheets; i++) {
                final XSSFSheet currentSheet = excelDoc.getSheetAt(i);
                final Iterator<Row> rowIterator = currentSheet.iterator();

                while (rowIterator.hasNext()) {
                    final Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();

                    while (cellIterator.hasNext()) {
                        final Cell cell = cellIterator.next();
                        CellType cellType = cell.getCellType();

                        if (cellType == CellType.NUMERIC) {
                            textBuilder.append(cell.getNumericCellValue());
                        }
                        else if (cellType == CellType.STRING) {
                            textBuilder.append(cell.getStringCellValue());
                        }

                        if (cellIterator.hasNext()) {
                            textBuilder.append(",");
                        }
                    }
                    textBuilder.append("\n");
                }
                textBuilder.append("\n");
            }

            excelDoc.close();

            return DocumentBuilder.newDocumentBuilder()
                    .setText(textBuilder.toString())
                    .setFontSize(DEFAULT_FONT_SIZE)
                    .setFontFamily(DEFAULT_FONT_FAMILY)
                    .setFilePath(filePath)
                    .build();
        }
        catch (Exception e) {
            return null;
        }
    }
}
