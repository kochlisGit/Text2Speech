package document.loader;

import document.builder.DocumentBuilder;
import document.entity.Document;

import java.io.BufferedReader;
import java.io.FileReader;

public class TextLoader implements DocumentLoader{
    private static final double DEFAULT_FONT_SIZE = 12;
    private static final String DEFAULT_FONT_FAMILY = "Arial";

    // Loads every line from a TXT file.
    @Override
    public Document loadDocument(String filePath) {
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(filePath));
            final StringBuilder textBuilder = new StringBuilder();
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                textBuilder.append(currentLine);
            }

            reader.close();

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
