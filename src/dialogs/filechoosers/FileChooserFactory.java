package dialogs.filechoosers;

public class FileChooserFactory {
    public static final int SAVE = 1;
    public static final int LOAD = 2;

    public static FileChooserDialog getNotifierDialog(final int dialogType) {
        switch (dialogType) {
            case SAVE:
                return new SaveFileChooserDialog();
            case LOAD:
                return new LoadFileChooserDialog();
            default:
                return null;
        }
    }
}
