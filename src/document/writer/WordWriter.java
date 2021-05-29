package document.writer;

import document.entity.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.FileOutputStream;

public class WordWriter implements DocumentWriter {

    // Creates a WORD document.
    @Override
    public boolean writeDocument(final Document document) {
        final XWPFDocument wordDoc = new XWPFDocument();
        final XWPFParagraph paragraph = wordDoc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        final XWPFRun run = paragraph.createRun();
        run.setFontSize(document.getFontSize());
        run.setFontFamily(document.getFontFamily());
        run.setText(document.getText());

        try {
            wordDoc.write(new FileOutputStream(document.getFilePath()));
            wordDoc.close();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
