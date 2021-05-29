package dialogs.notifiers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public abstract class NotifierDialog extends Alert {
    private static final String DEFAULT_TITLE = "Notifier";

    public NotifierDialog(final AlertType alertType, final String headerText, final String contentText) {
        super(alertType);
        super.setTitle(DEFAULT_TITLE);
        super.setHeaderText(headerText);
        super.setContentText(contentText);
    }

    public boolean showAndWaitResult() {
        final Optional<ButtonType> result = super.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
