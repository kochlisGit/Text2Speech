package document.writer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentWriterFactoryTest {
    private static final String TEXTFILE = "txt";
    private static final String WORD = "docx";
    private static final String EXCEL = "xlsx";

    @Test
    public void testDocumentWriterFactory() {
        DocumentWriter txtWriter = DocumentWriterFactory.getDocumentWriter(TEXTFILE);
        DocumentWriter wordWriter = DocumentWriterFactory.getDocumentWriter(WORD);
        DocumentWriter excelWriter = DocumentWriterFactory.getDocumentWriter(EXCEL);

        assertTrue(txtWriter instanceof TextWriter);
        assertTrue(wordWriter instanceof WordWriter);
        assertTrue(excelWriter instanceof ExcelWriter);
    }
}