package document.writer;

import document.entity.Document;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class TextWriter implements DocumentWriter {

    // Creates a simple TEXT document.
    public boolean writeDocument(final Document document) {
        try {
            final BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(document.getFilePath())));

            writer.write(document.getText());
            writer.close();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
