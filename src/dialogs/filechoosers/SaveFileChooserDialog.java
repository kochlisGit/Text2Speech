package dialogs.filechoosers;

import javafx.stage.Stage;

import java.io.File;

public class SaveFileChooserDialog extends FileChooserDialog {

    @Override
    protected File getFile(final Stage primaryStage) {
        return super.getFileChooser().showSaveDialog(primaryStage);
    }
}
