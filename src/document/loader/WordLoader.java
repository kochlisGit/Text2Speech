package document.loader;

import document.builder.DocumentBuilder;
import document.entity.Document;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.FileInputStream;

public class WordLoader implements DocumentLoader{
    private static final double DEFAULT_FONT_SIZE = 12;
    private static final String DEFAULT_FONT_FAMILY = "Arial";

    // Loads all paragraphs from a WORD file.
    @Override
    public Document loadDocument(String filePath) {

        try {
            final XWPFDocument wordDoc = new XWPFDocument(new FileInputStream(filePath));
            final XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(wordDoc);
            final String text = xwpfWordExtractor.getText();

            wordDoc.close();

            return DocumentBuilder.newDocumentBuilder()
                    .setText(text)
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
