package dialogs.notifiers;

import javafx.scene.control.Alert;

public class AudioAddedDialog extends NotifierDialog{
    private static final Alert.AlertType ALERT_TYPE = Alert.AlertType.INFORMATION;
    private static final String HEADER_TEXT = "Audio Document Created";
    private static final String CONTENT_TEXT = "A new audio document was created and stored in memory.";

    public AudioAddedDialog() {
        super(ALERT_TYPE, HEADER_TEXT, CONTENT_TEXT);
    }
}
