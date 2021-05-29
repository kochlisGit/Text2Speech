package dialogs.notifiers;

public class UnsavedFileDialog extends NotifierDialog {
    private static final AlertType ALERT_TYPE = AlertType.CONFIRMATION;
    private static final String HEADER_TEXT = "Unsaved Document Detected";
    private static final String CONTENT_TEXT = "Are You sure you want to proceed without saving the document?";

    public UnsavedFileDialog() {
        super(ALERT_TYPE, HEADER_TEXT, CONTENT_TEXT);
    }
}
