package document.builder;

import document.entity.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentBuilderTest {
    private static final String TEXT = "Software Engineering - Project: Text2Speech";
    private static final int FONT_SIZE = 12;
    private static final String FONT_FAMILY = "ARIAL";
    private static final String FILEPATH = "D:\\";
    private static final Document EXPECTED_DOC = new Document(TEXT, FONT_SIZE, FONT_FAMILY, FILEPATH);

    @Test
    public void testDocumentBuilder() {
        final Document builderDoc = DocumentBuilder.newDocumentBuilder()
                .setText(TEXT)
                .setFontSize(FONT_SIZE)
                .setFontFamily(FONT_FAMILY)
                .setFilePath(FILEPATH)
                .build();

        assertEquals(EXPECTED_DOC.getText(), builderDoc.getText());
        assertEquals(EXPECTED_DOC.getFontSize(), builderDoc.getFontSize());
        assertEquals(EXPECTED_DOC.getFontFamily(), builderDoc.getFontFamily());
        assertEquals(EXPECTED_DOC.getFilePath(), builderDoc.getFilePath());
    }
}