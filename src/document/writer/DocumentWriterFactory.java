package document.writer;

public class DocumentWriterFactory {
    private static final String TEXTFILE = "txt";
    private static final String WORD = "docx";
    private static final String EXCEL = "xlsx";

    // Decides which loader to choose, based on file extension.
    public static DocumentWriter getDocumentWriter(final String extension) {
        if (extension == null)
            return null;

        switch (extension) {
            case TEXTFILE:
                return new TextWriter();
            case WORD:
                return new WordWriter();
            case EXCEL:
                return new ExcelWriter();
            default:
                return null;
        }
    }
}
