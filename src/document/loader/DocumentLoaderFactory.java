package document.loader;

public class DocumentLoaderFactory {
    private static final String TEXTFILE = "txt";
    private static final String WORD = "docx";
    private static final String EXCEL = "xlsx";

    // Decides which loader to choose, based on file extension.
    public static DocumentLoader getDocumentLoader(final String extension) {
        if (extension == null)
            return null;

        switch (extension) {
            case TEXTFILE:
                return new TextLoader();
            case WORD:
                return new WordLoader();
            case EXCEL:
                return new ExcelLoader();
            default:
                return null;
        }
    }
}
