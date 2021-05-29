package document.entity;

public class Document {
    private final String text;
    private final double fontSize;
    private final String fontFamily;
    private final String filePath;

    public Document(String text, double fontSize, String fontFamily, String filePath) {
        this.text = text;
        this.fontSize = fontSize;
        this.fontFamily = fontFamily;
        this.filePath = filePath;
    }

    public String getText() {
        return text;
    }

    public double getFontSize() {
        return fontSize;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public String getFilePath() {
        return filePath;
    }
}
