package dialogs.notifiers;

public class UnknownExtensionDialog extends NotifierDialog {
    private static final AlertType ALERT_TYPE = AlertType.ERROR;
    private static final String HEADER_TEXT = "Unknown file extension.";
    private static final String CONTENT_TEXT = "Ooops, It looks like You tried to import an unknown file.";

    public UnknownExtensionDialog() {
        super(ALERT_TYPE, HEADER_TEXT, CONTENT_TEXT);
    }
}
