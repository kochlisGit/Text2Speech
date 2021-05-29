package document.builder;

import document.entity.Document;

public class DocumentBuilder {
    private String text;
    private double fontSize;
    private String fontFamily;
    private String filePath;

    public static DocumentBuilder newDocumentBuilder() {
        return new DocumentBuilder();
    }

    public DocumentBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public DocumentBuilder setFontSize(double fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public DocumentBuilder setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    public DocumentBuilder setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public Document build() {
        return new Document(
                text,
                fontSize,
                fontFamily,
                filePath
        );
    }
}
