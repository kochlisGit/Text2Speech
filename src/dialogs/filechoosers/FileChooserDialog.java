package dialogs.filechoosers;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public abstract class FileChooserDialog {
    private static final String TITLE = "Select a Filepath";
    private static final String TXT_FILES = "*.txt";
    private static final String WORD_FILES = "*.docx";
    private static final String EXCEL_FILES = "*.xlsx";
    private static final String ALL_FILES = "*.*";

    private final FileChooser fileChooser;

    public FileChooserDialog() {
        fileChooser = new FileChooser();
        fileChooser.setTitle(TITLE);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text files", TXT_FILES),
                new FileChooser.ExtensionFilter("Word files", WORD_FILES),
                new FileChooser.ExtensionFilter("Excel files", EXCEL_FILES),
                new FileChooser.ExtensionFilter("All files", ALL_FILES)
        );
    }

    protected FileChooser getFileChooser() {
        return fileChooser;
    }

    public String getFilePath(final Stage primaryStage) {
        File selectedFile = getFile(primaryStage);

        if (selectedFile != null) {
            return selectedFile.getAbsolutePath();
        }
        return null;
    }

    protected abstract File getFile(final Stage primaryStage);
}
