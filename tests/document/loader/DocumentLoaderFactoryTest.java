package document.loader;

import document.writer.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentLoaderFactoryTest {
    private static final String TEXTFILE = "txt";
    private static final String WORD = "docx";
    private static final String EXCEL = "xlsx";

    @Test
    public void testDocumentLoaderFactory() {
        DocumentLoader txtLoader = DocumentLoaderFactory.getDocumentLoader(TEXTFILE);
        DocumentLoader wordLoader = DocumentLoaderFactory.getDocumentLoader(WORD);
        DocumentLoader excelLoader = DocumentLoaderFactory.getDocumentLoader(EXCEL);

        assertTrue(txtLoader instanceof TextLoader);
        assertTrue(wordLoader instanceof WordLoader);
        assertTrue(excelLoader instanceof ExcelLoader);
    }
}