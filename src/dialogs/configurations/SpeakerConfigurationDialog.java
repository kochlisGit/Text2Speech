package dialogs.configurations;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import java.util.HashMap;
import java.util.Optional;

public class SpeakerConfigurationDialog {
    private static final String TITLE = "Audio Configuration";
    private static final String HEADER = "Edit Speaker Configurations";
    private static final String GLYPH_STYLE = "-fx-text-fill: #ff1744;";

    private final Dialog<HashMap<String, String>> dialog;

    private final ComboBox<String> voiceCmb;
    private final Slider rateSlider;
    private final Slider pitchSlider;
    private final Slider volumeSlider;

    public SpeakerConfigurationDialog() {
        dialog = new Dialog<>();
        voiceCmb = new ComboBox<>();
        rateSlider = new Slider();
        pitchSlider = new Slider();
        volumeSlider = new Slider();

        setTextContent();
        setGraphicContent();
        setButtons();
        setNodeContent();
        setResultConverter();
    }

    private void setTextContent() {
        dialog.setTitle(TITLE);
        dialog.setHeaderText(HEADER);
    }

    private void setGraphicContent() {
        final Glyph glyph = new Glyph("FontAwesome", FontAwesome.Glyph.PLAY_CIRCLE);
        glyph.setStyle(GLYPH_STYLE);
        dialog.setGraphic(glyph);
    }

    private void setButtons() {
        dialog.getDialogPane().getButtonTypes().addAll(
                ButtonType.OK,
                ButtonType.CANCEL
        );
    }

    private void setNodeContent() {
        final GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        final Label voiceLabel = new Label("Name:");
        final Label rateLabel = new Label("Rate:");
        final Label pitchLabel = new Label("Pitch:");
        final Label volumeLabel = new Label("Volume:");

        voiceCmb.getItems().addAll("kevin", "kevin16");
        voiceCmb.getSelectionModel().selectFirst();

        rateSlider.setMin(100);
        rateSlider.setMax(250);
        rateSlider.setValue(190);
        rateSlider.setMinorTickCount(1);
        rateSlider.setMajorTickUnit(10);
        rateSlider.setSnapToTicks(true);
        rateSlider.setShowTickMarks(true);
        rateSlider.setShowTickLabels(true);

        pitchSlider.setMin(100);
        pitchSlider.setMax(200);
        pitchSlider.setValue(150);
        pitchSlider.setMinorTickCount(1);
        pitchSlider.setMajorTickUnit(10);
        pitchSlider.setSnapToTicks(true);
        pitchSlider.setShowTickMarks(true);
        pitchSlider.setShowTickLabels(true);

        volumeSlider.setMin(0);
        volumeSlider.setMax(100);
        volumeSlider.setValue(100);
        volumeSlider.setMinorTickCount(1);
        volumeSlider.setMajorTickUnit(10);
        volumeSlider.setSnapToTicks(true);
        volumeSlider.setShowTickMarks(true);
        volumeSlider.setShowTickLabels(true);

        grid.add(voiceLabel, 0, 0);
        grid.add(voiceCmb, 1, 0);
        grid.add(rateLabel, 0, 1);
        grid.add(rateSlider, 1, 1);
        grid.add(pitchLabel, 0, 2);
        grid.add(pitchSlider, 1, 2);
        grid.add(volumeLabel, 0, 3);
        grid.add(volumeSlider, 1, 3);

        dialog.getDialogPane().setContent(grid);
    }

    private void setResultConverter() {
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new HashMap<>() {{
                    put("voice", voiceCmb.getValue());
                    put("rate", String.valueOf((int) rateSlider.getValue()));
                    put("pitch", String.valueOf((int) pitchSlider.getValue()));
                    put("volume", String.valueOf((int) volumeSlider.getValue()));
                }};
            }
            return null;
        });
    }

    public HashMap<String, String> getDialogResult() {
        Optional<HashMap<String, String>> result = dialog.showAndWait();
        return result.orElse(null);
    }
}
