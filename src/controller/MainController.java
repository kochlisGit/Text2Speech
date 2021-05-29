package controller;

import audio.builder.VoiceBuilder;
import audio.entity.AudioDocument;
import audio.speaker.SequenceSpeaker;
import com.sun.speech.freetts.Voice;
import dialogs.configurations.SpeakerConfigurationDialog;
import dialogs.filechoosers.FileChooserFactory;
import dialogs.notifiers.NotifierDialogFactory;
import document.builder.DocumentBuilder;
import document.entity.Document;
import document.loader.DocumentLoader;
import document.loader.DocumentLoaderFactory;
import document.writer.DocumentWriter;
import document.writer.DocumentWriterFactory;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.controlsfx.glyphfont.Glyph;
import utilities.CursorUtils;
import utilities.FilenameUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainController {
    private Stage primaryStage;
    private double mouseX, mouseY;

    private Document document;
    private List<AudioDocument> audioDocumentList;
    private boolean documentChanged;

    @FXML
    private TextArea textArea;
    @FXML
    private Slider fontSizeSlider;
    @FXML
    private ToggleGroup fontFamilyGroup;
    @FXML
    private RadioMenuItem recordingRadioItem;
    @FXML
    private MenuItem convertSpeechItem;
    @FXML
    private MenuItem replaySequenceItem;
    @FXML
    private Label cursorLabel;
    @FXML
    private Label fontSizeLabel;
    @FXML
    private Label fontFamilyLabel;
    @FXML
    private Label recordingLabel;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void initialize() {
        resetEditor();
    }

    @FXML
    public void titleBarBtnHover(MouseEvent mouseEvent) {
        final Glyph glyph = (Glyph) mouseEvent.getSource();
        Color glyphColor = (Color) glyph.getTextFill();
        glyph.setEffect(new DropShadow(15, glyphColor));
    }

    @FXML
    public void nodeHoverReset(MouseEvent mouseEvent) {
        ((Node) mouseEvent.getSource()).setEffect(null);
    }

    @FXML public void titleBarMousePressed(MouseEvent mouseEvent) {
        mouseX = primaryStage.getX() - mouseEvent.getScreenX();
        mouseY = primaryStage.getY() - mouseEvent.getScreenY();
    }

    @FXML
    public void titleBarMouseDragged(MouseEvent mouseEvent) {
        primaryStage.setX(mouseEvent.getScreenX() + mouseX);
        primaryStage.setY(mouseEvent.getScreenY() + mouseY);
    }

    @FXML
    public void titleBarMouseClicked(MouseEvent mouseEvent) {
        if ( mouseEvent.getButton().equals(MouseButton.PRIMARY) )
            if (mouseEvent.getClickCount() == 2)
                maxBtnClicked();
    }

    @FXML
    public void minBtnClicked() {
        primaryStage.setIconified(true);
    }

    @FXML
    public void maxBtnClicked() {
        if (!primaryStage.isFullScreen()) {
            primaryStage.setFullScreenExitHint("");
            primaryStage.setFullScreen(true);
        }
        else {
            primaryStage.setFullScreen(false);
        }
    }

    @FXML
    public void changeDetected() {
        documentChanged = true;
        updateStatusBar();
    }

    @FXML
    public void newDocument() {
        if (documentChanged &&
                !NotifierDialogFactory.getNotifierDialog(NotifierDialogFactory.UNSAVED_FILE).showAndWaitResult()) {
            return;
        }

        resetEditor();
    }

    @FXML
    public void loadDocument() {
        if (documentChanged &&
                !NotifierDialogFactory.getNotifierDialog(NotifierDialogFactory.UNSAVED_FILE).showAndWaitResult()) {
            return;
        }

        final String filePath = FileChooserFactory.getNotifierDialog(FileChooserFactory.LOAD).getFilePath(primaryStage);
        if (filePath != null) {
            final String extension = new FilenameUtils().getExtension(filePath);
            final DocumentLoader documentLoader = DocumentLoaderFactory.getDocumentLoader(extension);

            if (documentLoader != null) {
                document = documentLoader.loadDocument(filePath);
                documentChanged = false;

                textArea.setText(document.getText());
            }
            else {
                NotifierDialogFactory.getNotifierDialog(NotifierDialogFactory.UNKNOWN_EXTENSION).showAndWaitResult();
            }
        }
    }

    @FXML
    public void saveDocument() {
        if (documentChanged) {
            if (document == null) {
                saveDocumentAs();
            } else {
                final String filePath = document.getFilePath();
                updateDocument(filePath);
                saveDocumentToFile(filePath);
            }
        }
    }

    @FXML
    public void saveDocumentAs() {
        final String filePath = FileChooserFactory.getNotifierDialog(FileChooserFactory.SAVE).getFilePath(primaryStage);
        if (filePath != null) {
            updateDocument(filePath);
            saveDocumentToFile(filePath);
        }
    }

    @FXML
    public void exitBtnClicked() {
        if (documentChanged &&
                !NotifierDialogFactory.getNotifierDialog(NotifierDialogFactory.UNSAVED_FILE).showAndWaitResult()) {
            return;
        }
        primaryStage.close();
    }

    @FXML
    public void changeFont() {
        double fontSize = fontSizeSlider.getValue();
        final String fontFamily = ((RadioMenuItem) fontFamilyGroup.getSelectedToggle()).getText();
        textArea.setFont(new Font(fontFamily, fontSize));

        updateStatusBar();
    }

    @FXML
    public void checkRecordingOptions() {
        convertSpeechItem.setDisable(!recordingRadioItem.isSelected() || textArea.getSelectedText().length() < 1);
        replaySequenceItem.setDisable(!recordingRadioItem.isSelected() || audioDocumentList.size() < 1);
    }

    @FXML
    public void enableRecording() {
        audioDocumentList = new ArrayList<>();
        updateStatusBar();
    }

    @FXML
    public void convertTextToSpeech() {
        if (recordingRadioItem.isSelected()) {
            String selectedText = textArea.getSelectedText();
            if (selectedText.length() > 0) {
                audioDocumentList.add(new AudioDocument(selectedText));
                NotifierDialogFactory.getNotifierDialog(NotifierDialogFactory.AUDIO_ADDED).showAndWaitResult();
            }
        }
    }

    @FXML
    public void replaySequence() {
        if (audioDocumentList.size() > 0) {
            HashMap<String, String> audioConfigMap = new SpeakerConfigurationDialog().getDialogResult();

            if (audioConfigMap != null) {
                Voice voice = VoiceBuilder.newVoiceBuilder()
                        .setVoiceName(audioConfigMap.get("voice"))
                        .setVoiceRate(Integer.parseInt(audioConfigMap.get("rate")))
                        .setVoicePitch(Integer.parseInt(audioConfigMap.get("pitch")))
                        .setVoiceVolume(Integer.parseInt(audioConfigMap.get("volume")))
                        .build();
                if (voice != null) {
                    new SequenceSpeaker().replaySequence(voice, audioDocumentList);
                }
            }
        }
    }

    @FXML
    public void openGuide() {
        final String guideFilePath = System.getProperty("user.dir") + File.separator + "Text2Speech.pdf";

        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(guideFilePath);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    public void updateStatusBar() {
        final int offset = textArea.getCaretPosition();
        CursorUtils cursorUtils = new CursorUtils();
        final int row = cursorUtils.getCurrentRow(textArea.getText(), offset);
        final int column = cursorUtils.getCurrentColumn(textArea.getText(), offset);
        cursorLabel.setText(String.format("Row: %d, Col: %d", row, column));

        final int fontSize = (int) fontSizeSlider.getValue();
        fontSizeLabel.setText(String.format("Font Size: %d", fontSize));

        final String fontFamily = ((RadioMenuItem) fontFamilyGroup.getSelectedToggle()).getText();
        fontFamilyLabel.setText(String.format("Font Family: %s", fontFamily));

        if (recordingRadioItem.isSelected()) {
            recordingLabel.setText("Recording: ON");
        }
        else {
            recordingLabel.setText("Recording: OFF");
        }
    }

    private void updateDocument(String filePath) {
        document = DocumentBuilder.newDocumentBuilder()
                .setText(textArea.getText())
                .setFontSize(fontSizeSlider.getValue())
                .setFontFamily(((RadioMenuItem) fontFamilyGroup.getSelectedToggle()).getText())
                .setFilePath(filePath)
                .build();
    }

    private void saveDocumentToFile(String filePath) {
        String extension = new FilenameUtils().getExtension(filePath);
        DocumentWriter documentWriter = DocumentWriterFactory.getDocumentWriter(extension);

        if (documentWriter != null) {
            documentWriter.writeDocument(document);
            documentChanged = false;
        }
        else {
            NotifierDialogFactory.getNotifierDialog(NotifierDialogFactory.UNKNOWN_EXTENSION).showAndWaitResult();
        }
    }

    private void resetEditor() {
        textArea.clear();
        updateStatusBar();

        document = null;
        audioDocumentList = new ArrayList<>();
        documentChanged = false;
    }
}
