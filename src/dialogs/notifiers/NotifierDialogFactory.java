package dialogs.notifiers;

public class NotifierDialogFactory {
    public static final int AUDIO_ADDED = 1;
    public static final int UNKNOWN_EXTENSION = 2;
    public static final int UNSAVED_FILE = 3;
    
    public static NotifierDialog getNotifierDialog(final int dialogType) {
        switch (dialogType) {
            case AUDIO_ADDED:
                return new AudioAddedDialog();
            case UNKNOWN_EXTENSION:
                return new UnknownExtensionDialog();
            case UNSAVED_FILE:
                return new UnsavedFileDialog();
            default:
                return null;
        }
    }
}
